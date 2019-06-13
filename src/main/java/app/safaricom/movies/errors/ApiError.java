package app.safaricom.movies.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class ApiError {

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    private String message;

    private String debugMessage;

    private List<ApiSubError> subErrors;

    public ApiError() {
        this.timestamp = new Date();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    private void addSubError(ApiSubError subError) {
        if (subErrors == null) {
            subErrors = new ArrayList<>();
        }
        subErrors.add(subError);
    }

    /**
     * Record field validation errors
     */
    public void addFieldValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(error -> {
            addSubError(new ApiValidationError(
                    error.getObjectName(),
                    error.getField(),
                    error.getRejectedValue(),
                    error.getDefaultMessage()
            ));
        });
    }

    /**
     * Record global validation errors
     * @param globalErrors
     */
    public void addGlobalValidationErrors(List<ObjectError> globalErrors) {
        globalErrors.forEach(error -> {
            addSubError(new ApiValidationError(
                    error.getObjectName(),
                    error.getDefaultMessage()
            ));
        });
    }


    /**
     * Record constraint validation errors
     * @param constraintViolations
     */
    public void addConstraintValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(error -> {
            addSubError(new ApiValidationError(
                    error.getRootBeanClass().getSimpleName(),
                    ((PathImpl) error.getPropertyPath()).getLeafNode().asString(),
                    error.getInvalidValue(),
                    error.getMessage()
            ));
        });
    }
}
