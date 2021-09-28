package ChanuE.MovieTheater.domain.movieapi;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@ToString
@XmlRootElement(name = "movieListResult")
public class MovieListResult {

    @XmlElement(name = "totCnt")
    private String totCnt;

    @XmlElement(name = "movieList")
    private MovieList movieList;

    @Getter
    @ToString
    @XmlRootElement(name = "movieList")
    public static class MovieList {

        @XmlElement(name = "movie")
        private List<MovieApiDomain> movies;

    }

}
