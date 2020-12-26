package ChanuE.MovieTheater.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private String city;
    private String detailedAddress;

    public Address(String city, String detailedAddress) {
        this.city = city;
        this.detailedAddress = detailedAddress;
    }
}
