package pl.notes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service", url = "http://auth-service:8081/api/auth")
public interface AuthServiceClient {

    @PostMapping("/signup")
    void signup(@RequestBody RegisterRequest request);

    @PostMapping("/authenticate")
    AuthenticationResponse authenticate(@RequestBody RegisterRequest request);

}
