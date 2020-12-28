package ChanuE.MovieTheater.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class SpecificArea extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specific_area_id")
    private Long id;

    @Column(nullable = false)
    private String specificAreaName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_id")
    private Area area;

    @OneToMany(mappedBy = "specificArea")
    private List<Date> dates = new ArrayList<>();

    @Builder
    public SpecificArea(String specificAreaName) {
        this.specificAreaName = specificAreaName;
    }

    // == 연관 관계 메서드 == //
    // (항상 Many 쪽에!!)
    public void setArea(Area area){
        this.area = area;
        area.getSpecificAreas().add(this);
    }

}
