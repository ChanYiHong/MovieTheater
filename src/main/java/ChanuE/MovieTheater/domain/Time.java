package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Time extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;
    private int seats;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cinema cinema;
}
