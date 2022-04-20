package kg.peaksoft.peaksoftlmsbb4.mapper.link;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.link.LinkRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Link;
import org.springframework.stereotype.Component;

@Component
public class LinkMapper implements Converter<Link, LinkRequest, LinkResponse> {
    @Override
    public Link convert(LinkRequest linkRequest) {
        Link link=new Link();
        link.setLink(linkRequest.getLink());
        link.setText(linkRequest.getText());
        return link;
    }

    @Override
    public LinkResponse deConvert(Link link) {
        LinkResponse linkResponse=new LinkResponse();
        linkResponse.setId(link.getId());
        linkResponse.setLink(link.getLink());
        linkResponse.setText(link.getText());
        return linkResponse;
    }
}
