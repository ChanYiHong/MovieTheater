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
    private String detailedAddress;

    public Address(String city, String detailedAddress) {
        this.city = city;
        this.detailedAddress = detailedAddress;
    }
}
