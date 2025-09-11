package tech.marcel.naissances.Shared.Esception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ApplicationControllerAdvice {


    @ResponseStatus(value = NOT_FOUND)
    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody ErrorEntity runtimeExceptionHandler(RuntimeException runtimeException){
        return new ErrorEntity(
                LocalDateTime.now(),
                null,
                runtimeException.getMessage(),
                NOT_FOUND.value()
        )  ;
    }
}