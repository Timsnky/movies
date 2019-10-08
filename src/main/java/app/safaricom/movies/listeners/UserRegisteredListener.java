package app.safaricom.movies.listeners;

import app.safaricom.movies.events.UserRegistered;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UserRegisteredListener implements ApplicationListener<UserRegistered> {

    @Override
    public void onApplicationEvent(UserRegistered userRegistered) {
        System.out.println("Received Event : " + userRegistered.getUser().toString());
    }
}
