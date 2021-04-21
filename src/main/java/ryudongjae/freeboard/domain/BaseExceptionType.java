package ryudongjae.freeboard.domain;

public interface BaseExceptionType {
    int getErrorCode();
    int getHttpStatus();
    String getErrorMessage();
}
