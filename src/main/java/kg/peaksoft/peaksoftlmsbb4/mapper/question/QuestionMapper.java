package kg.peaksoft.peaksoftlmsbb4.mapper.question;

import kg.peaksoft.peaksoftlmsbb4.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.dto.question.QuestionRequest;
import kg.peaksoft.peaksoftlmsbb4.dto.question.QuestionResponse;
import kg.peaksoft.peaksoftlmsbb4.mapper.variant.VariantMapper;
import kg.peaksoft.peaksoftlmsbb4.model.Question;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Component
public class QuestionMapper implements Converter<Question, QuestionRequest, QuestionResponse> {

    private final VariantMapper variantMapper;

    @Override
    public Question convert(QuestionRequest questionRequest) {
        Question question = new Question();
        question.setQuestion(questionRequest.getQuestion());
        return question;
    }

    @Override
    public QuestionResponse deConvert(Question question) {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setId(question.getId());
        questionResponse.setQuestion(question.getQuestion());
        questionResponse.setVariantResponses(variantMapper.deConvert(question.getVariants()));
        return questionResponse;
    }

    public List<QuestionResponse> deConvert(List<Question> questions) {
        List<QuestionResponse> questionResponses = new ArrayList<>();
        for (Question q : questions) {
            questionResponses.add(deConvert(q));
        }
        return questionResponses;
    }
}
