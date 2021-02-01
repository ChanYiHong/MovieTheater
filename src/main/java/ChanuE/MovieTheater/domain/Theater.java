package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;

/** 영화와 지역 간의 다:다 관계를 풀어낸 연결 엔티티 (1:다, 다:1) **/
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"movie", "area"})
public class Theater extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

}
