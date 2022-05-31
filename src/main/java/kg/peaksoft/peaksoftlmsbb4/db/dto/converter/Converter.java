package kg.peaksoft.peaksoftlmsbb4.db.dto.converter;

public interface Converter<MODEL, REQUEST, RESPONSE> {
    MODEL convert(REQUEST request);

    RESPONSE deConvert(MODEL model);


}
