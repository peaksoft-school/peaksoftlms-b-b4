package kg.peaksoft.peaksoftlmsbb4.db.service.impl;

import kg.peaksoft.peaksoftlmsbb4.db.dto.result.GetResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultRequest;
import kg.peaksoft.peaksoftlmsbb4.db.dto.result.ResultResponse;
import kg.peaksoft.peaksoftlmsbb4.db.mapper.result.ResultMapper;
import kg.peaksoft.peaksoftlmsbb4.db.model.Result;
import kg.peaksoft.peaksoftlmsbb4.db.model.Student;
import kg.peaksoft.peaksoftlmsbb4.db.model.Variant;
import kg.peaksoft.peaksoftlmsbb4.db.repository.ResultRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsbb4.db.repository.VariantRepository;
import kg.peaksoft.peaksoftlmsbb4.db.service.ResultService;
import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final VariantRepository variantRepository;
    private final ResultMapper resultMapper;
    private final StudentRepository studentRepository;

    @Override
    public ResultResponse saveResult(String email, ResultRequest resultRequest) {
        Variant variant = variantRepository.getById(resultRequest.getVariantId());
        Student student = studentRepository.findStudentByUserEmail(email);
            resultRequest.setStudentAnswer(variant.getOption());
            resultRequest.setIsTrue(variant.getAnswer());
            Result convert = resultMapper.convert(resultRequest);
            convert.setStudent(student);
            Result save = resultRepository.save(convert);
            return resultMapper.deConvert(save);
    }
    @Override
    public ResultResponse findById(Long id) {
        Result byId = resultRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        "this id =%s not found"));
        log.info("successful find by id:{}", byId);
        return resultMapper.deConvert(byId);
    }

    @Override
    public List<ResultResponse> findAll() {
        List<Result> all = resultRepository.findAll();
        log.info("successful find all Results:{}", all);
        return resultMapper.deConvert(all);
    }

    @Override
    public void delete(Long id) {
        resultRepository.deleteById(id);
        log.info("successful delete this id:{}", id);
    }
    @Override
    public GetResultResponse getResults(String email) {
        GetResultResponse getResultResponse = new GetResultResponse();
        Student studentByUserEmail = studentRepository.findStudentByUserEmail(email);
        getResultResponse.setStudentName(studentByUserEmail.getStudentName());
        long wrongAnswerCounter = 0;
        long correctAnswerCounter = 0;
        for (Result r : resultRepository.getResultsByStudentId(studentByUserEmail.getId())) {
            if (r.getIsTrue()) {
                correctAnswerCounter++;
            } else {
                wrongAnswerCounter++;
            }
        }
        getResultResponse.setWrongAnswer(wrongAnswerCounter);
        getResultResponse.setCorrect(correctAnswerCounter);
        long results = wrongAnswerCounter + correctAnswerCounter;
        Long process = (getResultResponse.getCorrect() * 100) / results;
        getResultResponse.setProcess(process);
        log.info("successful results this student:{}", studentByUserEmail);
        return getResultResponse;
    }
}
