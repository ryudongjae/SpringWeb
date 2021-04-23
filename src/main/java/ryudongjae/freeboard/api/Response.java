package ryudongjae.freeboard.api;


import lombok.Getter;
import lombok.NoArgsConstructor;
import ryudongjae.freeboard.domain.BaseExceptionType;

@NoArgsConstructor
@Getter
public class Response<T> {

    private boolean hasError;
    private BaseExceptionType exceptionType;
    private T responseDto;

    public boolean hasError(){
        return hasError;
    }

    public static Response ok () {
        Response response = new Response();
        response.hasError = false;
        return response;
    }

    public static<T> Response ok (T responseDto){
        Response response = new Response();
        response.hasError = false;
        response.responseDto = responseDto;
        return response;
    }

    public static Response error (BaseExceptionType exceptionType){
        Response response = new Response();
        response.hasError = true;
        response.exceptionType = exceptionType;
        return response;
    }

}
