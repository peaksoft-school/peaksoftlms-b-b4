package kg.peaksoft.peaksoftlmsbb4.service;

import kg.peaksoft.peaksoftlmsbb4.dto.link.LinkRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsbb4.model.Link;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LinkService {
    LinkResponse saveLinks(Long id,LinkRequest linkRequest);

    LinkResponse findById(Long id);

    List<LinkResponse> findAll();

    LinkResponse update(Long id, LinkRequest linkRequest);

    void delete(Long id);
}
