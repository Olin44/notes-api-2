package pl.notes;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class FrontendController {

    @GetMapping("/notes")
    public String getNotes(Model model) {
        List<Note> notes = fetchNotes();  // Pobierz notatki z notes-service
        model.addAttribute("notes", notes); // Dodaj notatki do modelu
        return "notes";
    }

    private List<Note> fetchNotes() {
        // Wywołaj API notes-service (np. za pomocą RestTemplate lub FeignClient)
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Note[]> response = restTemplate.getForEntity("http://localhost:8082/api/v1/notes", Note[].class);
        return Arrays.asList(response.getBody());
    }
}
