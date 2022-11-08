package io.liu.catnip.mvc.web;

import io.liu.catnip.exception.BusinessException;
import io.liu.catnip.model.APIResponseMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableExceptionException(HttpServletRequest request,
                                                                       HttpServletResponse response,
                                                                       HttpMessageNotReadableException e) {

        HttpStatus httpStatus = HttpStatus.resolve(400);

        return ResponseEntity.status(httpStatus != null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR)
                .body(APIResponseMap.FAILED(-1, e.getMessage()));

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(NestedServletException.class)
    public ResponseEntity<Map<String, Object>> handleNestedServletExceptionException(HttpServletRequest request,
                                                                                              HttpServletResponse response,
                                                                                              HttpMessageNotReadableException e) {

        HttpStatus httpStatus = HttpStatus.resolve(500);

        return ResponseEntity.status(httpStatus != null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR)
                .body(APIResponseMap.FAILED(-1, e.getMessage()));

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(HttpServletRequest request,
                                                                       HttpServletResponse response,
                                                                       BusinessException e) {

        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());

        return ResponseEntity.status(httpStatus != null ? httpStatus : HttpStatus.INTERNAL_SERVER_ERROR)
                .body(APIResponseMap.FAILED(-1, e.getMessage()));

    }
}
