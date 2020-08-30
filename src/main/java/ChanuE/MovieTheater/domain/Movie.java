package ChanuE.MovieTheater.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Movie {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "time")
    private List<TimeTable> timeTables = new ArrayList<>();

    @Builder
    public Movie(String name){
        this.name = name;
    }
}
