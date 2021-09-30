package ChanuE.MovieTheater.dto.movie;

import ChanuE.MovieTheater.domain.AgeLimit;
import ChanuE.MovieTheater.domain.Movie;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class MovieRequestDTO {

    private String title;
    private String director;
    private String description;
    private AgeLimit ageLimit;
    private int runningTime;
    private List<MultipartFile> imageFiles;

}
