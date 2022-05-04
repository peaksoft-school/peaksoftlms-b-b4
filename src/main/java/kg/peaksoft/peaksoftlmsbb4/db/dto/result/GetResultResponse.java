package kg.peaksoft.peaksoftlmsbb4.db.dto.result;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetResultResponse {
    private String studentName;
    private Long wrongAnswer;
    private Long correct;
    private Long process;
    private Long points;
}
