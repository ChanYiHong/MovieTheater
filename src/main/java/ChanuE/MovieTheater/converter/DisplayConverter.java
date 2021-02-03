package ChanuE.MovieTheater.converter;

import ChanuE.MovieTheater.domain.Display;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DisplayConverter implements AttributeConverter<Display, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Display attribute) {
        if(attribute == Display.TWO_D) return 2;
        else if(attribute == Display.THREE_D) return 3;
        else if(attribute == Display.FOUR_D) return 4;
        else return 2;
    }

    @Override
    public Display convertToEntityAttribute(Integer dbData) {
        if(dbData == 2) return Display.TWO_D;
        else if(dbData == 3) return Display.THREE_D;
        else if(dbData == 4) return Display.FOUR_D;
        else return Display.TWO_D;
    }
}
