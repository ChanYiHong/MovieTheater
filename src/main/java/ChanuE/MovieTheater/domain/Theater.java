package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;

/** 영화관 (지역 정보 포함) **/
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString()
public class Theater extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "theater_id")
    private Long id;

    private String name;
    private String area;
    private String specificArea;
}
