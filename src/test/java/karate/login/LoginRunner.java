package karate.login;

import com.intuit.karate.junit5.Karate;

public class LoginRunner {

    @Karate.Test
    Karate testLogin() {
        return Karate.run("login").relativeTo(getClass());
    }
}
