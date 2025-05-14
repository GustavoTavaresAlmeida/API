package APIsustentavel.API.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class ValidationErrorResponse extends ErrorResponse{
    private List<String> errors;
    private Map<String, String> fieldErrors;

    public ValidationErrorResponse(int status, String message, LocalDateTime timestamp,
                                   List<String> errors, Map<String, String> fieldErrors) {
        super(status, message, timestamp);
        this.errors = errors;
        this.fieldErrors = fieldErrors;
    }
});

                return new ResponseEntity<>(ErrorResponse, HttpStatus.BAD_REQUEST);
        }

@ExceptionHandler(ConstraintViolationException.class)
public ResponseEntity<ValidationErrorResponse> handleConstraintViolation(
        ConstraintViolationException ex, WebRequest request) {

    List<String> errors = ex.getConstraintViolations()
            .stream()
            .map(ConstraintViolation::getMessage)
            .collect(Collectors.toList());

    ValidationErrorResponse errorResponse = new ValidationErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Erro de validação",
            LocalDateTime.now(),
            errors
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
}

@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorResponse> handleGlobalException(
        Exception ex, WebRequest request) {

    ErrorResponse errorResponse = new ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            "Ocorreu um erro interno no servidor",
            LocalDateTime.now()
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
}
}
