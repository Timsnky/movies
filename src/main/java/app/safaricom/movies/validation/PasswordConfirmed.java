package app.safaricom.movies.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Constraint(validatedBy = PasswordConfirmedValidator.class)
public @interface PasswordConfirmed {

    String message() default "Passwords entered do not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
