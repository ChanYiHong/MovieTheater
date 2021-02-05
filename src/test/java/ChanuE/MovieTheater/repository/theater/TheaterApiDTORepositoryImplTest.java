package ChanuE.MovieTheater.repository.theater;

import ChanuE.MovieTheater.dto.theater.TheaterAreaApiDTO;
import ChanuE.MovieTheater.dto.theater.TheaterSpecAreaApiDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TheaterApiDTORepositoryImplTest {

    @Autowired
    TheaterApiDTORepository theaterApiDTORepository;

    @Test
    public void searchAllAreaWithSpecificAreaCount() throws Exception {
        List<TheaterAreaApiDTO> result = theaterApiDTORepository.findAllArea();

        for (TheaterAreaApiDTO theaterAreaApiDTO : result) {
            System.out.println(theaterAreaApiDTO);
        }
    }

    @Test
    public void searchAllSpecificByArea() throws Exception {
        List<TheaterSpecAreaApiDTO> result = theaterApiDTORepository.findAllSpecificAreaByArea("서울");

        for (TheaterSpecAreaApiDTO theaterSpecAreaApiDTO : result) {
            System.out.println(theaterSpecAreaApiDTO);
        }
    }
}