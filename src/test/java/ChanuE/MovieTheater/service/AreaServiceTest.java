package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.domain.Area;
import ChanuE.MovieTheater.domain.Movie;
import ChanuE.MovieTheater.dto.area.AreaResponseDto;
import ChanuE.MovieTheater.repository.area.AreaSearch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AreaServiceTest {

    @Autowired
    AreaService areaService;

    @Autowired
    EntityManager em;

    @Test
    public void searchArea() throws Exception {

        Movie movie = Movie.builder().movieName("movie1").build();
        Area area1 = Area.builder().areaName("area1").build();
        Area area2 = Area.builder().areaName("area2").build();
        area1.setMovie(movie);
        area2.setMovie(movie);

        em.persist(movie);
        em.persist(area1);
        em.persist(area2);

        AreaSearch areaSearch = new AreaSearch();
        areaSearch.setMovieName("movie1");

        List<AreaResponseDto> result = areaService.findAllByAreaSearch(areaSearch);

        for (AreaResponseDto areaResponseDto : result) {
            System.out.println(areaResponseDto.getId());
            System.out.println(areaResponseDto.getName());
        }

    }

}