package ChanuE.MovieTheater.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Date extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate localDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specific_area_id")
    private SpecificArea specificArea;

    @Builder
    public Date(LocalDate localDate){
        this.localDate = localDate;
    }

    @OneToMany(mappedBy = "date")
    private List<TimeTable> timeTables = new ArrayList<>();


    // == 연관 관계 메서드 == //
    public void setSpecificArea(SpecificArea specificArea){
        this.specificArea = specificArea;
        specificArea.getDates().add(this);
    }
}
