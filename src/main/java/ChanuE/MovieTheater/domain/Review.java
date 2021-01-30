package ChanuE.MovieTheater.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"movie"})
public class Review extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String content;
    private int grade;
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    public Movie movie;

    /** 리뷰 내용은 수정 가능하게 **/
    public void changeContent(String content) {
        this.content = content;
    }

}
