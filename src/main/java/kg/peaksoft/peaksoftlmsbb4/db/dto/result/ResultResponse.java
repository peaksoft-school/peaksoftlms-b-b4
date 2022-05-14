package kg.peaksoft.peaksoftlmsbb4.db.dto.result;

import kg.peaksoft.peaksoftlmsbb4.db.enums.Result;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ResultResponse {
    private Long id;
    private Result result;
    private LocalDateTime dateOfPassed;
    private int grade;
}
