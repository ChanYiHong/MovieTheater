package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.MovieImage;
import ChanuE.MovieTheater.dto.movie.MovieApiSaveDTO;
import ChanuE.MovieTheater.dto.movie.MovieResponseDTO;
import ChanuE.MovieTheater.dto.movie.MovieRequestDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.movie.MovieSearch;
import ChanuE.MovieTheater.security.dto.AuthMemberDTO;
import ChanuE.MovieTheater.service.movie.MovieService;
import ChanuE.MovieTheater.upload.FileStore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movies/")
@Log4j2
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final FileStore fileStore;

    @Value("${HCY.api.clientId}")
    private String clientId;
    @Value("${HCY.api.clientSecret}")
    private String clientKey;

    @GetMapping("/list")
    public String movieList(@ModelAttribute("MovieSearch") MovieSearch movieSearch,
                            PageRequestDTO pageRequestDTO, Model model){
        log.info("Get Movie List");
        PageResponseDTO<Object[], MovieResponseDTO> result = movieService.list(pageRequestDTO, movieSearch);
        model.addAttribute("result", result);
        return "/movies/movie_list";
    }

    @GetMapping("/create")
    public String createMovie(@ModelAttribute MovieRequestDTO movieRequestDTO){
        log.info("Get Movie Create View");
        return "/movies/movie_create";
    }

    @PostMapping("/create")
    public String saveMovie(@ModelAttribute MovieRequestDTO movieRequestDTO) throws IOException {
        log.info("이름 : " + movieRequestDTO.getTitle());

        List<MultipartFile> imageFiles = movieRequestDTO.getImageFiles();

        for (MultipartFile imageFile : imageFiles) {
            log.info("이미지 이름 : {}", imageFile.getOriginalFilename());
            if (!imageFile.getContentType().startsWith("image")) {
                log.warn("This file is not image type");
                return "/movies/movie_create";
            }
        }

        List<MovieImage> movieImages = fileStore.storeFiles(imageFiles);

        for (MovieImage movieImage : movieImages) {
            log.info("movieImage : {}", movieImage);
        }

        movieService.saveMovie(movieRequestDTO, movieImages);
        return "redirect:/admin";
    }

    // 사용자 영화 목록 화면 조회용.
    @GetMapping("/info")
    public String movieInfo(@ModelAttribute("MovieSearch") MovieSearch movieSearch,
                            PageRequestDTO pageRequestDTO, Model model) {
        log.info("Movie Information");
        PageResponseDTO<Object[], MovieResponseDTO> result = movieService.list(pageRequestDTO, movieSearch);
        model.addAttribute("result", result);
        return "/movies/movie_info";
    }

    // 사용자 영화 상세 정보창.
    @GetMapping("/{id}")
    public String readMovie(@ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO,
                            @PathVariable("id") Long id, @AuthenticationPrincipal AuthMemberDTO memberDTO, Model model) {
        log.info("Movie Specific");
        MovieResponseDTO findMovie = movieService.findOne(id);

        log.info(findMovie);
        model.addAttribute("result", findMovie);
//

        log.info(memberDTO);
        if (memberDTO != null) {
            model.addAttribute("member", memberDTO);
            return "/movies/movie_read_auth";
        }
        else {
            return "/movies/movie_read";
        }
    }


    // 영화 api 로 저장할 때 추가 정보 넣어주는 화면
    @GetMapping("/public/modify")
    public String publicMovieModify(@ModelAttribute MovieApiSaveDTO movieApiSaveDTO, Model model) {

        model.addAttribute("movieApiInfo", movieApiSaveDTO);
        return "/movies/movie_api_modify";

    }

    @PostMapping("/public/modify")
    public String publicMovieModify(@ModelAttribute MovieApiSaveDTO movieApiSaveDTO) {

        movieService.saveMovieApi(movieApiSaveDTO);
        return "redirect:/";

    }


    // == 관리자 권한 == //
    // 네이버 API 사용. 영화 검색.
    @GetMapping("/public/search")
    public String publicMovieSearch(@ModelAttribute MoviePublicSearch moviePublicSearch, Model model) {

        BufferedReader bufferedReader = null;
        HttpURLConnection connection = null;
        StringBuilder result;

        List<MovieInfo> movieInfoList = new ArrayList<>();

        log.info("publicMovieSearch : " + moviePublicSearch);

        if (moviePublicSearch.title == null) {
            return "/movies/movie_search";
        }

        try {
            StringBuilder urlBuilder = new StringBuilder("https://openapi.naver.com/v1/search/movie.json");
            urlBuilder.append("?" + URLEncoder.encode("query", "UTF-8") + "=" + URLEncoder.encode(moviePublicSearch.getTitle(), "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("yearfrom", "UTF-8") + "=" + URLEncoder.encode("2000", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("yearto", "UTF-8") + "=" + URLEncoder.encode("2021", "UTF-8"));

            URL url = new URL(urlBuilder.toString());

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Naver-Client-Id", clientId);
            connection.setRequestProperty("X-Naver-Client-Secret", clientKey);

            if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            result = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            log.info("Result : {}", result.toString());

            bufferedReader.close();
            connection.disconnect();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());
            JSONArray items = (JSONArray) jsonObject.get("items");

            System.out.println("total : " + jsonObject.get("total"));
            System.out.println("lastBuildDate : " + jsonObject.get("lastBuildDate"));
            System.out.println("display : " + jsonObject.get("display"));
            System.out.println("start : " + jsonObject.get("start"));

            for (int i = 0; i < items.size(); i++) {
                JSONObject item = (JSONObject) items.get(i);

                System.out.println("actor : " + item.get("actor"));
                System.out.println("image : " + item.get("image"));
                System.out.println("director : " + item.get("director"));
                System.out.println("subtitle : " + item.get("subtitle"));
                System.out.println("link : " + item.get("link"));
                System.out.println("title : " + item.get("title"));
                System.out.println("pubDate : " + item.get("pubDate"));
                System.out.println("userRating : " + item.get("userRating"));

                MovieInfo movieInfo = new MovieInfo();
                String title = (String) item.get("title");
                title = title.replaceAll("<b>", "");
                title = title.replaceAll("</b>", "");

                String director = (String) item.get("director");
                director = director.replaceAll("<b>", "");
                director = director.replaceAll("</b>", "");

                movieInfo.setActor((String) item.get("actor"));
                movieInfo.setImage((String) item.get("image"));
                movieInfo.setDirector(director);
                movieInfo.setSubtitle((String) item.get("subtitle"));
                movieInfo.setLink((String) item.get("link"));
                movieInfo.setTitle(title);
                movieInfo.setPubDate((String) item.get("pubDate"));
                movieInfo.setUserRating((String) item.get("userRating"));

                movieInfoList.add(movieInfo);
            }
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        model.addAttribute("movies", movieInfoList);

        return "/movies/movie_search";
    }


    // Kobis Api List (Image x)
    //@GetMapping("/public/search")
    public String publicMovieSearchKobis(@ModelAttribute MoviePublicSearch moviePublicSearch, Model model) {

        log.info("publicMovieSearch : " + moviePublicSearch);

        if (moviePublicSearch.title == null) {
            return "/movies/movie_search";
        }

        List<MovieInfoKobis> movieInfoKobisList = new ArrayList<>();

        try {
            StringBuilder urlBuilder = new StringBuilder("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json");
            urlBuilder.append("?" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode("782990556149f979dfb986fdbf70abf7", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("openStartDt", "UTF-8") + "=" + URLEncoder.encode("2000", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("openEndDt", "UTF-8") + "=" + URLEncoder.encode("2021", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("movieNm", "UTF-8") + "=" + URLEncoder.encode(moviePublicSearch.getTitle(), "UTF-8"));


            URL url = new URL(urlBuilder.toString());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader bufferedReader;

            if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            StringBuilder result = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            log.info("Result : {}", result.toString());

            bufferedReader.close();
            connection.disconnect();

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());
            JSONObject movieListResult = (JSONObject) jsonObject.get("movieListResult");
            JSONArray movieList = (JSONArray) movieListResult.get("movieList");

            for (int i = 0; i < movieList.size(); i++) {
                JSONObject movie = (JSONObject) movieList.get(i);

                MovieInfoKobis movieInfoKobis = new MovieInfoKobis();
                movieInfoKobis.setMovieCd((String) movie.get("movieCd"));
                movieInfoKobis.setMovieNm((String) movie.get("movieNm"));
                movieInfoKobis.setMovieNmEn((String) movie.get("movieNmEn"));
                movieInfoKobis.setPrdtYear((String) movie.get("prdtYear"));
                movieInfoKobis.setOpenDt((String) movie.get("openDt"));
                movieInfoKobis.setTypeNm((String) movie.get("typeNm"));
                movieInfoKobis.setPrdtStatNm((String) movie.get("prdtStatNm"));
                movieInfoKobis.setNationAlt((String) movie.get("nationAlt"));
                movieInfoKobis.setGenreAlt((String) movie.get("genreAlt"));
                movieInfoKobis.setRepNationNm((String) movie.get("repNationNm"));
                movieInfoKobis.setRepGenreNm((String) movie.get("repGenreNm"));

                movieInfoKobisList.add(movieInfoKobis);
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("movies", movieInfoKobisList);

        return "/movies/movie_search_kobis";

    }

    @Data
    static class MoviePublicSearch {
        private String title;
    }

    @Data
    static class MovieInfo {

        private String actor;
        private String image;
        private String director;
        private String subtitle;
        private String link;
        private String title;
        private String pubDate;
        private String userRating;

    }

    @Data
    static class MovieInfoKobis {

        private String movieCd;
        private String movieNm;
        private String movieNmEn;
        private String prdtYear;
        private String openDt;
        private String typeNm;
        private String prdtStatNm;
        private String nationAlt;
        private String genreAlt;
        private String repNationNm;
        private String repGenreNm;

    }
}
