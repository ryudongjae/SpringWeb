package ryudongjae.freeboard.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import ryudongjae.freeboard.domain.BaseExceptionType;

@ControllerAdvice
public class ExceptionHandler {

    @ResponseBody
    @org.springframework.web.bind.annotation.ExceptionHandler(ryudongjae.freeboard.util.exception.FreeBoardException.class)
    public ResponseEntity<Error> exception(ryudongjae.freeboard.util.exception.FreeBoardException exception){
        return new ResponseEntity<>(Error.create(exception.getExceptionType()), HttpStatus.OK);
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Error{
        private int code;
        private int status;
        private String message;

        static Error create(BaseExceptionType exception){
           return new Error(exception.getErrorCode(), exception.getHttpStatus(), exception.getErrorMessage());
        }
    }

}
