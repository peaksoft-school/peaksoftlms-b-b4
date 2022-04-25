package kg.peaksoft.peaksoftlmsbb4.db.mapper.link;

import kg.peaksoft.peaksoftlmsbb4.db.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.link.LinkRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsbb4.db.model.Link;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    
    public List<LinkResponse> deConvert(List<Link> links){
        List<LinkResponse> linkResponses = new ArrayList<>();
        for (Link l:links) {
            linkResponses.add(deConvert(l));
        }
        return linkResponses;
    }
}
