package app.safaricom.movies.validation;

import app.safaricom.movies.requests.SignupFormRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, Object> {

    @Override
    public void initialize(PasswordConfirmed constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        final SignupFormRequest formRequest = (SignupFormRequest) o;

        String password = formRequest.getPassword();

        String passwordConfirmation = formRequest.getPassword_confirmation();

        boolean isValid = false;

        if (password != null && passwordConfirmation != null) {
            isValid = password.equals(passwordConfirmation);
        }

        if (! isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    constraintValidatorContext.getDefaultConstraintMessageTemplate()
            ).addPropertyNode("password_confirmation").addConstraintViolation();
        }

        return  isValid;
    }
}
