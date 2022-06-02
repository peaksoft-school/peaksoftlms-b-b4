package kg.peaksoft.peaksoftlmsbb4.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Slf4j
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
        log.error("Not found");
    }

    public NotFoundException() {
    }
}
