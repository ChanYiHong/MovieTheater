package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"movie", "theater"})
public class Cinema extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    private Long id;

    private String name;

    private Display display;

    @OneToOne(fetch = FetchType.LAZY)
    private Movie movie;

    private int seats;

    @ManyToOne(fetch = FetchType.LAZY)
    private Theater theater;

}
