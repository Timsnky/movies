package app.safaricom.movies.events;

import app.safaricom.movies.model.User;
import org.springframework.context.ApplicationEvent;

public class UserRegistered extends ApplicationEvent {

    private User user;

    public UserRegistered(Object source, User user) {
        super(source);

        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
