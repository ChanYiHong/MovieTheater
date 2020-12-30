package ChanuE.MovieTheater.controller;

import ChanuE.MovieTheater.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDate;

@Component
@Profile("local")
@RequiredArgsConstructor
public class InitMovie {

    private final InitMovieService initMovieService;

    @PostConstruct
    public void init() {
        initMovieService.init();
    }

    @Component
    static class InitMovieService {

        @Autowired
        private EntityManager em;

        @Transactional
        public void init() {

            Movie movie1 = new Movie("반지의 제왕");
            Movie movie2 = new Movie("담보");

            Area area1 = new Area("서울");
            Area area2 = new Area("경기");

            SpecificArea specificArea1 = new SpecificArea("송파구");
            SpecificArea specificArea2 = new SpecificArea("강남구");

            Date date1 = new Date(LocalDate.of(2020,12,25));
            Date date2 = new Date(LocalDate.of(2020,12,26));

            area1.setMovie(movie1);
            area2.setMovie(movie1);

            specificArea1.setArea(area1);
            specificArea2.setArea(area1);

            date1.setSpecificArea(specificArea1);
            date2.setSpecificArea(specificArea1);

            Member member1 = Member.builder().memberName("찬의")
                    .address(new Address("Seoul", "Jamsil"))
                    .authority(Authority.ADMIN).build();
            Member member2 = Member.builder().memberName("웅아")
                    .address(new Address("Suwon", "Jungja"))
                    .authority(Authority.USER).build();

            Reservation reservation = Reservation.createReservation(member1, movie1);

            em.persist(movie1);
            em.persist(movie2);
            em.persist(area1);
            em.persist(area2);
            em.persist(specificArea1);
            em.persist(specificArea2);
            em.persist(date1);
            em.persist(date2);
            em.persist(member1);
            em.persist(member2);
            em.persist(reservation);
        }
    }
}
