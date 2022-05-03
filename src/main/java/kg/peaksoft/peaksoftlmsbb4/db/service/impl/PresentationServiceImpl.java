package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.presentation.PresentationRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.presentation.PresentationResponse;
import kg.peaksoft.peaksoftlmsbb4.db.service.PresentationService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.presentation.PresentationMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Lesson;
import kg.peaksoft.peaksoftlmsbb4.db.model.Presentation;
import kg.peaksoft.peaksoftlmsbb4.db.repository.LessonRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.PresentationRepository;
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
public class PresentationServiceImpl implements PresentationService {
    private final PresentationRepository presentationRepository;
    private final PresentationMapper presentationMapper;
    private final LessonRepository lessonRepository;
    private final AWSS3Service awss3Service;

    @Override
    public PresentationResponse savePresentation(PresentationRequest presentationRequest) {
         Lesson lessons = lessonRepository.findById(presentationRequest.getLessonId()).orElseThrow(() -> new NotFoundException(
                String.format("Lesson with id %s not found",presentationRequest.getLessonId())
        ));
        Presentation presentation = presentationMapper.convert(presentationRequest);
        Presentation save = presentationRepository.save(presentation);
        lessons.setPresentation(save);
        log.info("successfully save presentation:{}", presentation);
        return presentationMapper.deConvert(save);
    }

    @Override
    public PresentationResponse findById(Long id) {
        log.info("successfully find by id:{}", id);
        Presentation presentation = presentationRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found id=%s", id)));
        return presentationMapper.deConvert(presentation);
    }

    @Override
    public PresentationResponse update(Long id, PresentationRequest presentationRequest) {
        boolean exist = presentationRepository.existsById(id);
        if (!exist) {
            throw new NotFoundException(String.format("Not found id=%s", id));
        }
        Presentation presentation = getById(id);
        if (!presentation.getName().equals(presentationRequest.getName())) {
            presentation.setName(presentationRequest.getName());
        }
        if (!presentation.getDescription().equals(presentationRequest.getDescription())) {
            presentation.setDescription(presentationRequest.getDescription());
        }
        log.info("successfully update id:{}", id);
        return presentationMapper.deConvert(presentation);
    }

    @Override
    public List<PresentationResponse> findAll() {
        log.info("successfully find all presentations");
        return presentationRepository.findAll().stream().map(presentationMapper::deConvert).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        boolean exits = presentationRepository.existsById(id);
        if (!exits) {
            log.error("not found presentation with id:{}", id);
            throw new NotFoundException(String.format("Not found presentation with id=%s", id));
        }
        log.info("successfully delete presentation by id:{}", id);
        presentationRepository.deleteById(id);
    }

    private Presentation getById(Long id) {
        log.info("successfully get presentation by id:{}", id);
        return presentationRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Not found id=%s", id)));
    }
}
