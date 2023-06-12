package mrs.reserveApp.domain.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime date){
        return date == null ?  null : Timestamp.valueOf(date);
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp value){
        return value == null ? null : value.toLocalDateTime();
    }
}
