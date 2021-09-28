package ChanuE.MovieTheater.domain.movieapi;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@ToString
@XmlRootElement(name = "company")
public class Company {

    @XmlElement(name = "companyCd")
    private String companyCd;

    @XmlElement(name = "companyNm")
    private String companyNm;

}
