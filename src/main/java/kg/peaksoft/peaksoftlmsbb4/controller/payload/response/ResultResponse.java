package kg.peaksoft.peaksoftlmsbb4.controller.payload.response;

import kg.peaksoft.peaksoftlmsbb4.db.enums.Result;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ResultResponse {
    private Long id;
    private Result result;
    private LocalDate dateOfPassed;
    private int grade;
    private String studentFullName;
}
