package pl.notes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FrontendController {

    private final NotesServiceClient notesServiceClient;
    private final AuthServiceClient authServiceClient;

    @GetMapping("/notes")
    public String getNotes(@RequestHeader ("Authorization") String authorization, Model model) {
        System.out.println("YYYYY");
        System.out.println(authorization);
        NotePage notePage = notesServiceClient.getNotes(authorization,0, 10, "createdAt", "ASC");
        List<Note> notes = notePage.getContent();

        model.addAttribute("notes", notes);
        model.addAttribute("currentPage", notePage.getPage());
        model.addAttribute("totalPages", notePage.getTotalPages());

        return "frontendNotes"; // Zwróć widok z listą notatek
    }

    @GetMapping("/editnote/{id}")
    public String editNote(@PathVariable UUID id, Model model) {
        Note note = notesServiceClient.getNoteById(id); // Zmień to na odpowiednią metodę w kliencie Feign
        model.addAttribute("note", note);
        return "editNote"; // Zwraca widok, w którym możesz edytować notatkę
    }


    @PostMapping("/editnote/{id}")
    public String updateNote(@PathVariable UUID id, @ModelAttribute NoteUpdateRequest noteUpdateRequest) {
        notesServiceClient.updateNote(id, noteUpdateRequest);
        return "redirect:/notes"; // Przekierowanie na stronę z listą notatek
    }

    @PostMapping("/deletenote/{id}")
    public String deleteNote(@PathVariable UUID id) {
        notesServiceClient.deleteNoteById(id);
        return "redirect:/notes"; // Przekierowanie na stronę z listą notatek
    }

    @GetMapping("/addNote")
    public String showAddNoteForm(Model model) {
        model.addAttribute("note", new NoteCreateRequest()); // lub NoteUpdateRequest, w zależności od tego, jak chcesz modelować
        return "addNote"; // Nazwa widoku do wyświetlenia formularza
    }

    @PostMapping("/addNote")
    public String addNote(@ModelAttribute NoteCreateRequest noteCreateRequest) {
        notesServiceClient.addNote(noteCreateRequest); // Wywołanie metody do dodania notatki w NotesServiceClient
        return "redirect:/notes"; // Przekierowanie do listy notatek po dodaniu
    }

    @GetMapping("/register")
    public String showSignup(Model model) {
        model.addAttribute("user", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String signup(@ModelAttribute RegisterRequest request, Model model) {
        try {
            authServiceClient.signup(request);
            return "redirect:/login"; // Po udanej rejestracji przekierowanie na stronę logowania
        } catch (Exception e) {
            // Obsługa wyjątku (np. jeśli email już istnieje)
            model.addAttribute("errorMessage", "Rejestracja nie powiodła się. Spróbuj ponownie.");
            model.addAttribute("user", request); // Utrzymanie wprowadzonego emaila w formularzu
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("user", new RegisterRequest());
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody RegisterRequest request) {
        // Logika autoryzacji
        AuthenticationResponse token = authServiceClient.authenticate(request);

        System.out.println("XXX");
        System.out.println(token);

        if (token != null) {


            // Możesz także zwrócić inne dane, jeśli potrzebujesz
            return ResponseEntity.ok()
                    .body(token); // Możesz zmodyfikować to, co zwracasz w ciele odpowiedzi
        } else {
            throw new RuntimeException();
        }
    }




}
