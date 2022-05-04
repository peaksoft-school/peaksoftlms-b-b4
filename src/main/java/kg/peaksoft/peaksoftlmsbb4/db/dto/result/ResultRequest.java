package kg.peaksoft.peaksoftlmsbb4.db.dto.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import kg.peaksoft.peaksoftlmsbb4.db.model.Question;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.cfg.QuerySecondPass;

import java.util.List;

@Getter
@Setter
public class ResultRequest {
    @JsonIgnore
    private String studentAnswer;
    @JsonIgnore
    private Boolean isTrue;
    @JsonProperty("variant_id")
    private List<Long> variantId;
}
