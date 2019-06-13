package app.safaricom.movies.exception.custom;

public final class ExistingUserException extends RuntimeException {

    public ExistingUserException(String message)
    {
        super(message);
    }
}
