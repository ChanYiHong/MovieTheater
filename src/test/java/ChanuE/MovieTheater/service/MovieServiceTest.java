package ChanuE.MovieTheater.service;

import ChanuE.MovieTheater.dto.movie.MovieResponseDTO;
import ChanuE.MovieTheater.dto.page.PageRequestDTO;
import ChanuE.MovieTheater.dto.page.PageResponseDTO;
import ChanuE.MovieTheater.repository.movie.MovieSearch;
import ChanuE.MovieTheater.service.movie.MovieServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    MovieServiceImpl movieServiceImpl;

    @Test
    public void movieListTest() throws Exception {

        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResponseDTO<Object[], MovieResponseDTO> list = movieServiceImpl.list(pageRequestDTO, new MovieSearch());

        List<MovieResponseDTO> dtoList = list.getDtoList();

        dtoList.forEach(movieResponseDto -> System.out.println(movieResponseDto));

    }

}