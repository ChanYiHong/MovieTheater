package ChanuE.MovieTheater.upload;

import ChanuE.MovieTheater.domain.MovieImage;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class FileStore {

    @Value("${HCY.upload.path}")
    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public List<MovieImage> storeFiles(List<MultipartFile> multipartFiles) throws IOException {

        List<MovieImage> movieImages = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                MovieImage movieImage = storeFile(multipartFile);
                movieImages.add(movieImage);
            }
        }

        return movieImages;

    }

    public MovieImage storeFile(MultipartFile multipartFile){

        if (multipartFile.isEmpty()) {
            return null;
        }

        String originalFileName = multipartFile.getOriginalFilename();
        String fileName = originalFileName.substring(0, originalFileName.lastIndexOf("."));
        String folderPath = makeDir();

        String uuid = UUID.randomUUID().toString();
        String ext = extractExt(originalFileName);

        String path = fileDir + File.separator + folderPath + File.separator + uuid + "_" + fileName + "." + ext;

        log.info("saveName : {}", path);

        Path savePath = Paths.get(path);

        try{
            multipartFile.transferTo(savePath);

            // Create Thumbnail
            String thumbnailSaveName = fileDir + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName + "." + ext;
            File thumbnailFile = new File(thumbnailSaveName);
            Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100, 100);

        }catch(IOException e){
            e.printStackTrace();
        }

        return MovieImage.builder()
                .uuid(uuid)
                .imgName(originalFileName)
                .storeFileName(uuid + "." + ext)
                .path(fileDir + File.separator + folderPath)
                .build();

    }

    private String extractExt(String originalFileName) {
        int index = originalFileName.lastIndexOf('.');
        return originalFileName.substring(index + 1);
    }

    private String makeDir() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("\\", File.separator);

        File uploadPathFolder = new File(fileDir, folderPath);

        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

}
