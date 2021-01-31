package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.dto.movie.MovieResponseDto;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.movie.MovieSearch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieServiceTest {

    @Autowired MovieService movieService;

    @Test
    public void movieListTest() throws Exception {

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResponseDTO<Object[], MovieResponseDto> list = movieService.list(pageRequestDTO, new MovieSearch());

        List<MovieResponseDto> dtoList = list.getDtoList();

        dtoList.forEach(movieResponseDto -> System.out.println(movieResponseDto));

    }

}