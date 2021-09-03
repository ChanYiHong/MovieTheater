package ChanuE.MovieTheater.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@RestController
@Slf4j
public class MovieImageApiController {

    @Value("${HCY.upload.path}")
    private String fileDir;

    @GetMapping("/display")
    public ResponseEntity<byte[]> getImageFile(@RequestParam("filePath") String filePath) {

        ResponseEntity<byte[]> result = null;

        try {
            String srcFilePath = URLDecoder.decode(filePath, "UTF-8");

            log.info("srcFilePath : {}", srcFilePath);

            File file = new File(srcFilePath);

            HttpHeaders header = new HttpHeaders();

            // MIME type 처리.
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            // file data 처리.
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        }

        return result;

    }


}
