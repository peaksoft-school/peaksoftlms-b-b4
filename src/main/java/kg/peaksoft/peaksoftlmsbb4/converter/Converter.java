package kg.peaksoft.peaksoftlmsbb4.converter;

public interface Converter<MODEL,REQUEST, RESPONSE> {
    MODEL convert(REQUEST request);
    RESPONSE deConvert(MODEL model);


}
