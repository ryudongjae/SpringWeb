package ryudongjae.freeboard.util.exception;


import lombok.Getter;
import ryudongjae.freeboard.domain.BaseExceptionType;

public class FreeBoardException extends RuntimeException {

    @Getter
    private BaseExceptionType exceptionType;

    public FreeBoardException(BaseExceptionType exceptionType){
        super(exceptionType.getErrorMessage());
        this.exceptionType = exceptionType;
    }

}
