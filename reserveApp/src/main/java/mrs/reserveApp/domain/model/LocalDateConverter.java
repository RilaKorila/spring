package mrs.reserveApp.domain.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public Date convertToDatabaseColumn(LocalDate date){
        return date == null ?  null : Date.valueOf(date);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date value){
        return value == null ? null : value.toLocalDate();
    }
}
