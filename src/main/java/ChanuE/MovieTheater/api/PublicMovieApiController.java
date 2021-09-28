package ChanuE.MovieTheater.api;

import ChanuE.MovieTheater.domain.movieapi.MovieListResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/public/api/movies")
@Slf4j
public class PublicMovieApiController {

    @Value("${HCY.api.clientId}")
    private String clientId;

    @Value("${HCY.api.clientSecret}")
    private String clientKey;

    // xml 방식.
    @GetMapping("/publicMoviesKobis")
    public ResponseEntity<Map<String, MovieListResult>> getPublicMovies(){

        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        StringBuilder result = new StringBuilder();

        try {
            StringBuilder urlBuilder = new StringBuilder("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.xml");
            urlBuilder.append("?" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode("782990556149f979dfb986fdbf70abf7", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("openStartDt", "UTF-8") + "=" + URLEncoder.encode("2021", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("openEndDt", "UTF-8") + "=" + URLEncoder.encode("2021", "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("startCreate_dt", "UTF-8") + "=" + URLEncoder.encode("20210821", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("endCreateDt",StandardCharsets.UTF_8) + "=" + URLEncoder.encode("20210821", StandardCharsets.UTF_8)); /*한 페이지 결과 수*/

            URL url = new URL(urlBuilder.toString());

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            log.info("Result : {}", result.toString());

            bufferedReader.close();
        } catch (IOException e){
            e.printStackTrace();
        } finally{

            connection.disconnect();

        }
        Map<String, MovieListResult> map = new HashMap<>();

        try {
            String html = result.toString();

            JAXBContext jaxbContext = JAXBContext.newInstance(MovieListResult.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            MovieListResult movieListResult = (MovieListResult) unmarshaller.unmarshal(new StringReader(html));

            map.put("movieInfo", movieListResult);

            return new ResponseEntity<>(map, HttpStatus.OK);

        } catch (JAXBException e) {

            log.error("error 발생.. : ", e);

            map.put("vaccination", new MovieListResult());
            return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    // Json 방식.
    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieJson>> searchMovie(@RequestParam("query") String query) {

        log.info("movie search : {}", query);

        BufferedReader bufferedReader = null;
        HttpURLConnection connection = null;
        StringBuilder result;

        List<MovieJson> movieJsonList = new ArrayList<>();

        try {
            StringBuilder urlBuilder = new StringBuilder("https://openapi.naver.com/v1/search/movie.json");
            urlBuilder.append("?" + URLEncoder.encode("query", "UTF-8") + "=" + URLEncoder.encode(query, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("yearfrom", "UTF-8") + "=" + URLEncoder.encode("2021", "UTF-8"));
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

                MovieJson movieJson = new MovieJson();
                movieJson.setActor((String) item.get("actor"));
                movieJson.setImage((String) item.get("image"));
                movieJson.setDirector((String) item.get("director"));
                movieJson.setSubtitle((String) item.get("subtitle"));
                movieJson.setLink((String) item.get("link"));
                movieJson.setTitle((String) item.get("title"));
                movieJson.setPubDate((String) item.get("pubDate"));
                movieJson.setUserRating((String) item.get("userRating"));

                movieJsonList.add(movieJson);
            }
        }
        catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();
        }

        return new ResponseEntity<>(movieJsonList, HttpStatus.OK);
    }

    @Data
    static class MovieJson {

        private String actor;
        private String image;
        private String director;
        private String subtitle;
        private String link;
        private String title;
        private String pubDate;
        private String userRating;

    }

}
