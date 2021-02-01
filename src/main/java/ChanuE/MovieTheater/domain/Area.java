package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"specificAreas", "theaters"})
@Entity
public class Area extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "area")
    @Builder.Default
    private List<Theater> theaters = new ArrayList<>();

    @OneToMany(mappedBy = "area")
    @Builder.Default
    private List<SpecificArea> specificAreas = new ArrayList<>();

}
