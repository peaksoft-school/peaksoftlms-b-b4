package kg.peaksoft.peaksoftlmsbb4.db.mapper.question;

import kg.peaksoft.peaksoftlmsbb4.db.converter.Converter;
import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.question.QuestionResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.variant.VariantRequest;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.variant.VariantMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Question;
import kg.peaksoft.peaksoftlmsbb4.db.model.Variant;
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
        List<Variant>variant=new ArrayList<>();
        Question question = new Question();
        question.setQuestion(questionRequest.getQuestion());
        question.setQuestionType(questionRequest.getQuestionType());
        for (VariantRequest q:questionRequest.getVariants()) {
            variant.add(variantMapper.convert(q));
        }
        question.setVariants(variant);
        return question;
    }

    @Override
    public QuestionResponse deConvert(Question question) {
        QuestionResponse questionResponse = new QuestionResponse();
        questionResponse.setQuestionId(question.getId());
        questionResponse.setQuestion(question.getQuestion());
        questionResponse.setQuestionType(question.getQuestionType());
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
