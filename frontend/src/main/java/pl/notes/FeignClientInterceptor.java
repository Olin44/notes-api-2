package pl.notes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        // Pobierz token z localStorage lub innego źródła
        String token = TokenHolder.getToken();
        if (token != null) {
            template.header("Authorization", "Bearer " + token);
        }
    }
}
