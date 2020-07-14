package ChanuE.MovieTheater.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;


@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String city;
    private String email;

    public Address(String city, String email){
        this.city = city;
        this.email = email;
    }

}
