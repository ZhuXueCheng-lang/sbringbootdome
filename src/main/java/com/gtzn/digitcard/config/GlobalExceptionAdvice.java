package com.gtzn.digitcard.config;

import com.gtzn.digitcard.util.R;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
@ResponseBody
@Log4j2
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = ConstraintViolationException.class)
    public R validException(ConstraintViolationException exception) {
        log.error(String.valueOf(exception));
        Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation violation : violations) {
            builder.append(violation.getMessage());
            break;
        }

        return R.error("400",builder.toString());
    }
}