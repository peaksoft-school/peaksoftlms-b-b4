package kg.peaksoft.peaksoftlmsbb4.converter;

public interface MyConverter<MODEL,REQUEST,RESPONCE> {
    MODEL convert(Long id,REQUEST request);
    RESPONCE deConvert(MODEL model);


}
