package kg.peaksoft.peaksoftlmsbb4.db.converter;

public interface Converter<MODEL, REQUEST, RESPONSE> {
    MODEL convert(REQUEST request);

    RESPONSE deConvert(MODEL model);


}
