package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.link.LinkRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.link.LinkResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.link.LinkMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Lesson;
import kg.peaksoft.peaksoftlmsbb4.db.model.Link;
import kg.peaksoft.peaksoftlmsbb4.db.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.LinkRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.LinkService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
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
    public LinkResponse saveLinks(LinkRequest linkRequest) {
        System.out.println("This method works exactly");
        Lesson lessons = lessonRepository.findById(linkRequest.getLessonId()).orElseThrow(() -> new NotFoundException(
                String.format("Link with id %s not found", linkRequest.getLessonId())
        ));
        log.info("found lesson:{}", lessons);
        Link link = linkMapper.convert(linkRequest);
        log.info("found link:{}", lessons);
        Link save = linkRepository.save(link);
        log.info("save lesson:{}", lessons);
        lessons.setLink(save);
        log.info("successfully save links:{}", link);
        return linkMapper.deConvert(save);
    }

    @Override
    public LinkResponse findById(Long id) {
        log.info("successfully find link by id:{}", id);
        return linkMapper.deConvert(getLinkById(id));
    }

    @Override
    public LinkResponse update(Long id, LinkRequest linkRequest) {
        boolean exist = linkRepository.existsById(id);
        if (!exist) {
            log.error("not found link with id:{}", id);
            throw new NotFoundException(String.format("Link is not found with  id=%s", id));
        }
        Link link = getLinkById(id);
        if (!link.getLink().equals(linkRequest.getLink())) {
            link.setLink(linkRequest.getLink());
        }
        if (!link.getText().equals(linkRequest.getText())) {
            link.setText(linkRequest.getText());
        }
        log.info("successfully update link with id:{}", id);
        return linkMapper.deConvert(link);
    }

    @Override
    public String delete(Long id) {
        boolean exits = linkRepository.existsById(id);
        if (!exits) {
            log.error("not found link with id:{}", id);
            throw new NotFoundException(String.format("link is does not exists with id=%s", id));
        }
        log.info("successfully delete link by id:{}", id);
        linkRepository.deleteById(id);
        return "Link deleted";
    }

    @Override
    public LinkResponse findLinkByLessonId(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() ->
                new NotFoundException(
                        String.format("Lesson with id = %s not found", id)));
        return linkMapper.deConvert(lesson.getLink());
    }

    private Link getLinkById(Long id) {
        log.info("successfully find link by id:{}", id);
        return linkRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Link is not found id=%s", id)));
    }
}
