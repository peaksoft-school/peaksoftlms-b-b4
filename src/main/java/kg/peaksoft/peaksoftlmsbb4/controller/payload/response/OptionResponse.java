package kg.peaksoft.peaksoftlmsbb4.controller.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OptionResponse {
    private Long id;
    private String option;
    private boolean isCorrect;
}
