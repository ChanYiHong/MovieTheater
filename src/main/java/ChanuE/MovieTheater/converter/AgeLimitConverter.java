package ChanuE.MovieTheater.converter;

import ChanuE.MovieTheater.domain.AgeLimit;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class AgeLimitConverter implements AttributeConverter<AgeLimit, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AgeLimit attribute) {
        if(attribute.equals(AgeLimit.ALL)) return 0;
        else if(attribute.equals(AgeLimit.TWELVE)) return 12;
        else if(attribute.equals(AgeLimit.FIFTEEN)) return 15;
        else if(attribute.equals(AgeLimit.NINETEEN)) return 19;
        else return 0;
    }

    @Override
    public AgeLimit convertToEntityAttribute(Integer dbData) {
        if(dbData == 0) return AgeLimit.ALL;
        else if(dbData == 12) return AgeLimit.TWELVE;
        else if(dbData == 15) return AgeLimit.FIFTEEN;
        else if(dbData == 19) return AgeLimit.NINETEEN;
        else return AgeLimit.ALL;
    }
}
