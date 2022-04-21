package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto.link.LinkRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.mapper.link.LinkMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Lesson;
import kg.peaksoft.peaksoftlmsbb4.model.Link;
import kg.peaksoft.peaksoftlmsbb4.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsbb4.repository.LinkRepository;
import kg.peaksoft.peaksoftlmsbb4.service.LinkService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;
    private final LinkMapper linkMapper;
    private final LessonRepository lessonRepository;

    @Override
    public LinkResponse saveLinks(Long id, LinkRequest linkRequest) {
        System.out.println("This method works exactly");
        Lesson lessons = lessonRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Lesson with id %s not found", id)
        ));
        log.info("found lesson:{}", lessons);
        Link link = linkMapper.convert(linkRequest);
        log.info("found link:{}", lessons);
        Link save = linkRepository.save(link);
        log.info("saved lesson:{}", lessons);
        lessons.setLink(save);
        log.info("successfully save links:{}", link);
        return linkMapper.deConvert(save);
    }

    @Override
    public LinkResponse findById(Long id) {
        return linkMapper.deConvert(getLinkById(id));
    }

    @Override
    public List<LinkResponse> findAll() {
        log.info("successfully find all links");
        return linkRepository.findAll().stream().map(linkMapper::deConvert).collect(Collectors.toList());
    }

    @Override
    public LinkResponse update(Long id, LinkRequest linkRequest) {
        boolean exist = linkRepository.existsById(id);
        if (!exist) {
            throw new NotFoundException(String.format("Link is not found with  id=%s", id));
        }
        Link link = getLinkById(id);
        if (!link.getLink().equals(linkRequest.getLink())) {
            link.setLink(linkRequest.getLink());
        }
        if (!link.getText().equals(linkRequest.getText())) {
            link.setText(linkRequest.getText());
        }
        log.info("successfully update id:{}", id);
        return linkMapper.deConvert(link);
    }

    @Override
    public void delete(Long id) {
        boolean exits = linkRepository.existsById(id);
        if (!exits) {
            throw new NotFoundException(String.format("link is does not exists id=%s", id));
        }
        log.info("successfully delete by id:{}", id);
        linkRepository.deleteById(id);
    }

    private Link getLinkById(Long id) {
        log.info("successfully find by id:{}", id);
        return linkRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Link is not found id=%s", id)));
    }
}
