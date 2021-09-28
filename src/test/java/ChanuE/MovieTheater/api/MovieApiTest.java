package ChanuE.MovieTheater.api;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.charset.StandardCharsets;

@Slf4j
@SpringBootTest
public class MovieApiTest {

    @Value("${HCY.api.clientId}")
    private String clientId;

    @Value("${HCY.api.clientSecret}")
    private String clientKey;

    @Test
    void getMovieApiTest() throws IOException {

        StringBuilder urlBuilder = new StringBuilder("http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.xml");
        urlBuilder.append("?" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode("782990556149f979dfb986fdbf70abf7", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("openStartDt", "UTF-8") + "=" + URLEncoder.encode("2021", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("openEndDt", "UTF-8") + "=" + URLEncoder.encode("2021", "UTF-8"));
//        urlBuilder.append("&" + URLEncoder.encode("startCreate_dt", "UTF-8") + "=" + URLEncoder.encode("20210821", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("endCreateDt",StandardCharsets.UTF_8) + "=" + URLEncoder.encode("20210821", StandardCharsets.UTF_8)); /*한 페이지 결과 수*/

        URL url = new URL(urlBuilder.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        BufferedReader bufferedReader;

        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        else {
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

    }

    @Test
    void getNaverMovieApi() throws IOException, ParseException {

        StringBuilder urlBuilder = new StringBuilder("https://openapi.naver.com/v1/search/movie.json");
        urlBuilder.append("?" + URLEncoder.encode("query", "UTF-8") + "=" + URLEncoder.encode("아", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("yearfrom", "UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("yearto", "UTF-8") + "=" + URLEncoder.encode("2021", "UTF-8"));

        URL url = new URL(urlBuilder.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Naver-Client-Id", clientId);
        connection.setRequestProperty("X-Naver-Client-Secret", clientKey);

        BufferedReader bufferedReader;

        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        else {
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
        }



    }

}
