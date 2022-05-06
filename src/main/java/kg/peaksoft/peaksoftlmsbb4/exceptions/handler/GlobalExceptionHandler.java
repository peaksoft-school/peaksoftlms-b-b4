package kg.peaksoft.peaksoftlmsbb4.exceptions.handler;

import kg.peaksoft.peaksoftlmsbb4.exceptions.BadRequestException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.NotFoundException;
import kg.peaksoft.peaksoftlmsbb4.exceptions.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            MethodArgumentNotValidException.class,
            BadRequestException.class, NotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMethodArgumentNotValidException(RuntimeException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.BAD_REQUEST);
        exceptionResponse.setMessage(e.getMessage());
        return exceptionResponse;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleAnyExceptions(Exception e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND);
        exceptionResponse.setMessage(e.getMessage());
        return exceptionResponse;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ExceptionResponse handleBadCredentialsException(BadCredentialsException badCredentialsException) {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setStatus(HttpStatus.UNAUTHORIZED);
        exceptionResponse.setMessage(badCredentialsException.getMessage());
        return exceptionResponse;
    }
}
