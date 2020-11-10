package ChanuE.MovieTheater.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class SpecificArea {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specific_area_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    @Builder
    public SpecificArea(String name) {
        this.name = name;
    }

    // == 연관 관계 메서드 == //
    // (항상 Many 쪽에!!)
    public void setArea(Area area){
        this.area = area;
        area.getSpecificAreas().add(this);
    }

}
