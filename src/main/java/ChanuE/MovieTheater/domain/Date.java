package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"specificArea", "timeTables"})
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
    @Builder.Default
    private List<TimeTable> timeTables = new ArrayList<>();


    // == 연관 관계 메서드 == //
    public void setSpecificArea(SpecificArea specificArea){
        this.specificArea = specificArea;
        specificArea.getDates().add(this);
    }
}
