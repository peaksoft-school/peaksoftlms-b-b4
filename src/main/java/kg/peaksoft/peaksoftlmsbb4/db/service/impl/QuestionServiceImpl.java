package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.controller.payload.question.QuestionRequest;
import kg.peaksoft.peaksoftlmsbb4.controller.payload.question.QuestionResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.question.QuestionMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Question;
import kg.peaksoft.peaksoftlmsbb4.db.repository.QuestionRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.QuestionService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public QuestionResponse saveQuestion(QuestionRequest questionRequest) {
        String question = questionRequest.getQuestion();
        if (questionRepository.existsByQuestion(question)) {
            throw new BadRequestException(
                    String.format("Sorry, please try another question=%s", question)
            );
        }

        Question question1 = questionMapper.convert(questionRequest);
        questionRepository.save(question1);
        log.info("successful save question:{}", question);
        return questionMapper.deConvert(question1);
    }

    @Override
    public QuestionResponse findById(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("this id not found =%s", id)
        ));
        log.info("successful find by this id:{}", id);
        return questionMapper.deConvert(question);
    }

    @Override
    public List<QuestionResponse> findAll() {
        List<Question> repositoryAll = questionRepository.findAll();
        return questionMapper.deConvert(repositoryAll);
    }

    @Override
    public QuestionResponse update(Long id, QuestionRequest questionRequest) {
        boolean exists = questionRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException(
                    String.format("question id not found=%s", id)
            );
        }
        Question question = questionRepository.findById(id).orElseThrow(() -> new NotFoundException(
                String.format("this id not found=%s", id)
        ));
        String currentQuestion = question.getQuestion();
        String newQuestion = questionRequest.getQuestion();
        if (!currentQuestion.equals(newQuestion)) {
            question.setQuestion(newQuestion);
        }
        log.info("successful update :{}", question);
        return questionMapper.deConvert(question);
    }

    @Override
    public void delete(Long id) {
        questionRepository.deleteById(id);
        log.info("successful delete this id:{}", id);
    }
}
