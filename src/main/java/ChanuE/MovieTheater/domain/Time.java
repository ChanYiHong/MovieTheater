package ChanuE.MovieTheater.domain;

import lombok.*;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"cinema", "seats"})
public class Time extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id")
    private Long id;

    @Column(nullable = false)
    private LocalTime time;

    @Column(nullable = false)
    private int seatNum;

    @Column(nullable = false)
    private int availableNum;

    // Time이 삭제되면, 연관된 Seat도 전부 삭제되도록 설정.
    @OneToMany(mappedBy = "time", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Seat> seats = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Cinema cinema;

    public void setAvailableNum(int availableNum) {
        this.availableNum = availableNum;
    }
}
