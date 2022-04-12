package kg.peaksoft.peaksoftlmsbb4.converter;

public interface Converter<MODEL,REQUEST,RESPONCE> {
    MODEL convert(REQUEST request);
    RESPONCE deConvert(MODEL model);


}
