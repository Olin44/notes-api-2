package pl.notes.app.exceptions;

public record ErrorResponse(String message, String reason, int codeHTTP) {

}
