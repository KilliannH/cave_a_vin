package fr.eni.cave.exception;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@AllArgsConstructor
public class AppExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(value = { Exception.class})
    public ResponseEntity<String> capturerException(Exception ex) {
        return new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<String> capturerMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                          Locale locale) {
        final String titreMsg = messageSource.getMessage("notvalidexception", null, locale);

        String message = ex
                .getFieldErrors()
                .stream()
                .map(e -> e.getDefaultMessage())
                .reduce(titreMsg, String::concat);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
