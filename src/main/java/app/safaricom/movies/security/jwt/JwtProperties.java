package app.safaricom.movies.security.jwt;

public class JwtProperties {

    public static final String SECRET = "SafaricomMoviesSecret";
    public static final int EXPIRATION_TIME = 7200000;   //2HRS
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
