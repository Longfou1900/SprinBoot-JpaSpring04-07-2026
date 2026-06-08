package co.istad.longfou.restapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@RestControllerAdvice
@Slf4j
public class AppException {

    //response status for
//    @ResponseStatus(ResponseStatusException.class)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleServiceExceoption(ResponseStatusException e)
    {
        ErrorResponse<?> errorResponse = ErrorResponse.builder()
                .status(false)
                .code(e.getStatusCode().value())
                .message("Service excetion errored")
                .errors(e.getReason())
                .build();
        return ResponseEntity.status(e.getStatusCode())
                .body(errorResponse);
    }
    //MethodArgumentNotValidException = Bad request
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleValidationEception(MethodArgumentNotValidException e){
    public ErrorResponse<?> handleValidationEception(MethodArgumentNotValidException e){

        log.error("Validation Eception happpened");

//        List<Map<String, String>> errors = new ArrayList<>();
        List<FieldErrorResponse> fields =  new ArrayList<>();

        e.getFieldErrors()
                .forEach(fieldError -> {
                    FieldErrorResponse field = FieldErrorResponse.builder()
                            .field(fieldError.getField())
                            .message(fieldError.getDefaultMessage())
                            .build();
                    fields.add(field);
                });
        return  ErrorResponse.builder()
                .status(false)
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Validation Error")
                .errors(fields)
                .build();


//        e.getBindingResult()
//                .getFieldErrors()
//                .forEach(error -> {

//                    Map<String, String> fieldError = new HashMap<>();
//
//                    fieldError.put("field", error.getField());
//                    fieldError.put("message", error.getDefaultMessage());
//
//                    errors.add(fieldError);
//                });

//        Map<String, Object> response = new HashMap<>();
//        response.put("status", false);
//        response.put("code", 400);
//        response.put("messsage", "Validation Failed");
//        response.put("errors", errors);
////        response.put("errors", "price is required");
//
//        return ResponseEntity
//                .badRequest()
//                .body(response);
    }
}
