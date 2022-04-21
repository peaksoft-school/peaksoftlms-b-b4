package kg.peaksoft.peaksoftlmsbb4.service.impl;

import kg.peaksoft.peaksoftlmsbb4.dto.link.LinkRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsbb4.exception.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.mapper.link.LinkMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Lessons;
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
        Lessons lessons = lessonRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("Lesson with id %s not found", id)
        ));
        Link link = linkMapper.convert(linkRequest);
        Link save = linkRepository.save(link);
        lessons.setLink(save);
        log.info("successfully save links:{}", link);
        return linkMapper.deConvert(save);
    }

    @Override
    public Link findById(Long id) {
        log.info("successfully find by id:{}", id);
        return linkRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found id=%s", id)));
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
            throw new NotFoundException(String.format("Not found id=%s", id));
        }
        Link link = findById(id);
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
            throw new NotFoundException(String.format("Not found id=%s", id));
        }
        log.info("successfully delete by id:{}", id);
        linkRepository.deleteById(id);
    }
}
