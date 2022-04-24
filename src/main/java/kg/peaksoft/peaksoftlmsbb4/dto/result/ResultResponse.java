package kg.peaksoft.peaksoftlmsbb4.dto.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultResponse {
    private Long id;
    private Boolean correct;
    private Boolean error;
}
