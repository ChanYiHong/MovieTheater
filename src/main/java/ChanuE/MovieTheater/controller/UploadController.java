package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.dto.upload.UploadResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {

    @Value("${HCY.upload.path}") // application.yml의 변수
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResponseDTO>> uploadFile(MultipartFile[] uploadFiles){

        log.info("uploadPath "+uploadPath);

        List<UploadResponseDTO> responseDTOList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {

            // Can upload image file only
            if(uploadFile.getContentType().startsWith("image") == false){
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            String originalFilename = uploadFile.getOriginalFilename();
            String fileName = originalFilename.substring(originalFilename.lastIndexOf("\\") + 1);

            log.info(fileName);

            String folderPath = makeFolder();

            //UUID
            String uuid = UUID.randomUUID().toString();

            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            log.info("saveName = " + saveName);

            Path savePath = Paths.get(saveName);

//            try{
//                uploadFile.transferTo(savePath);
//
//                // Create Thumbnail
//                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;
//                File thumbnailFile = new File(thumbnailSaveName);
//                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);
//
//                responseDTOList.add(new UploadResponseDTO(fileName, uuid, folderPath));
//            }catch(IOException e){
//                e.printStackTrace();
//            }
        }
        return new ResponseEntity<>(responseDTOList, HttpStatus.OK);
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(@RequestParam("fileName") String fileName) {
        ResponseEntity<byte[]> result = null;

        try{
            log.info("fileName: " + fileName);

            String srcFileName = "";

            //String srcFileName = URLDecoder.decode(fileName, StandardCharsets.UTF_8);
            log.info("srcFileName: " + srcFileName);

            File file = new File(uploadPath + File.separator + srcFileName);
            log.info("file: " + file);

            HttpHeaders header = new HttpHeaders();

            // MIME type 처리.
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            // file data 처리.
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

        }catch (Exception e){
            log.error("error: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName){
        String srcFileName = null;

        try{
            srcFileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            boolean result = file.delete();

            File thumbnail = new File(file.getParent(), "s_" + file.getName());
            result = thumbnail.delete();
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("\\", File.separator);

        // make folder
        File uploadPathFolder = new File(uploadPath, folderPath);

        log.info(uploadPathFolder);

        if(uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

}
