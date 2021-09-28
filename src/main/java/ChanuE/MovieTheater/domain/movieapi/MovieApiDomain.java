package ChanuE.MovieTheater.domain.movieapi;

import lombok.Getter;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Getter
@ToString
@XmlRootElement(name = "movie")
public class MovieApiDomain {

    @XmlElement(name = "movieCd")
    private String movieCd;

    @XmlElement(name = "movieNm")
    private String movieNm;

    @XmlElement(name = "movieNmEn")
    private String movieNmEn;

    @XmlElement(name = "prdtYear")
    private String prdtYear;

    @XmlElement(name = "openDt")
    private String openDt;

    @XmlElement(name = "typeNm")
    private String typeNm;

    @XmlElement(name = "prdtStatNm")
    private String prdtStatNm;

    @XmlElement(name = "nationAlt")
    private String nationAlt;

    @XmlElement(name = "genreAlt")
    private String genreAlt;

    @XmlElement(name = "repNationNm")
    private String repNationNm;

    @XmlElement(name = "repGenreNm")
    private String repGenreNm;

    @XmlElement(name = "directors")
    private Directors directors;

    @XmlElement(name = "companys")
    private Companies companies;

    @Getter
    @ToString
    @XmlRootElement(name = "directors")
    public static class Directors {

        @XmlElement(name = "director")
        private List<Director> directors;

    }

    @Getter
    @ToString
    @XmlRootElement(name = "companys")
    public static class Companies {

        @XmlElement(name = "company")
        private List<Company> companies;

    }


}
