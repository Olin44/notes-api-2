package pl.notes.app.auth;

import lombok.Builder;


@Builder
public record RegisterRequest (String email, String password) {

}
