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
        Student student = studentRepository.findStudentByUserEmail(email);
        List<Variant> variants = variantRepository.findAllById(resultRequest.getVariantId());
        int counter=0;
        for (Variant variant : variants) {
            resultRequest.setStudentAnswer(variant.getOption());
            System.err.println("hello error");
            if (variant.getAnswer()){
                counter++;
                System.err.println("hello error2");
                resultRequest.setIsTrue(variant.getAnswer());
            }else if (counter>4){
                throw new BadRequestException("error");
            }


            Result convert = resultMapper.convert(resultRequest);
            convert.setStudent(student);
            Result save = resultRepository.save(convert);
            return resultMapper.deConvert(save);
        }
        return null;
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
        List<Variant> all1 = variantRepository.findAll();
        List<Result> all = resultRepository.findAll();
        for (Result result:all) {
            result.setVariants(all1);
            log.info("successful find all Results:{}", all);
            return resultMapper.deConvert(all);
        }
        return null;
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
        long results = wrongAnswerCounter + correctAnswerCounter;
        long points = correctAnswerCounter * 2;
        getResultResponse.setWrongAnswer(wrongAnswerCounter);
        getResultResponse.setCorrect(correctAnswerCounter);
        Long process = (getResultResponse.getCorrect() * 100) / results;
        getResultResponse.setProcess(process);
        getResultResponse.setPoints(points);
        log.info("successful results this student:{}", studentByUserEmail);
        return getResultResponse;
    }

    public List<ResultResponse> saveResult1(String email, List<ResultRequest> resultRequest) {
        Student student = studentRepository.findStudentByUserEmail(email);
        for (ResultRequest f : resultRequest) {
            List<Variant> variants = variantRepository.findAllById(f.getVariantId());
            for (Variant variant : variants) {
                f.setStudentAnswer(variant.getOption());
                f.setIsTrue(variant.getAnswer());
            }
            List<Result> results = resultMapper.convert1(resultRequest);
            for (Result r : results) {
                r.setStudent(student);
            }
            List<Result> results1 = resultRepository.saveAll(results);
            return resultMapper.deConvert(results1);
        }
        return null;
    }
}
