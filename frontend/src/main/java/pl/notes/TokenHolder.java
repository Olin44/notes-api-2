package pl.notes;

import org.springframework.stereotype.Component;

@Component
public class TokenHolder {
    private static String token;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        TokenHolder.token = token;
    }
}
