package ChanuE.MovieTheater.repository.area;

import ChanuE.MovieTheater.domain.Area;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.repository.movie.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AreaSpringDataJpaRepositoryImplTest {

    @Autowired
    AreaSpringDataJpaRepository areaRepository;
    @Autowired
    MovieRepository movieRepository;

    @Test
    public void findAllAreaByMovieId() throws Exception {

        //given
        Movie movie = Movie.builder().movieName("movie1").build();
        Area area1 = new Area("area1");
        Area area2 = new Area("area2");
        Area area3 = new Area("area3");

        area1.setMovie(movie);
        area2.setMovie(movie);
        area3.setMovie(movie);
        movieRepository.save(movie);
        areaRepository.save(area1);
        areaRepository.save(area2);
        areaRepository.save(area3);

        //when
        List<Area> result = areaRepository.findAllAreaByMovieId(movie.getId());

        //then
//        assertThat(result.size()).isEqualTo(3);
//        assertThat(result.get(0)).extracting("areaName").containsExactly("area1");

        for (Area area : result) {
            System.out.println("Area = " + area.getAreaName());
        }

    }

}