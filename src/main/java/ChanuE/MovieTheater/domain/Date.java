package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"cinema", "times"})
public class Date extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "date_id")
    private Long id;

    private LocalDate localDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cinema cinema;

    @OneToMany(mappedBy = "date")
    @Builder.Default
    private List<Time> times = new ArrayList<>();


}
