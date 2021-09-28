package ChanuE.MovieTheater.domain.movieapi;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@ToString
@XmlRootElement(name = "director")
public class Director {

    @XmlElement(name = "peopleNm")
    private String peopleNm;


}
