package pl.notes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class FrontendController {

    private final NotesServiceClient notesServiceClient;

    @GetMapping("/notes")
    public String getNotes(Model model) {
        NotePage notePage = notesServiceClient.getNotes(0, 10, "createdAt", "ASC");
        List<Note> notes = notePage.getContent();

        model.addAttribute("notes", notes);
        model.addAttribute("currentPage", notePage.getPage());
        model.addAttribute("totalPages", notePage.getTotalPages());

        return "notes"; // Zwróć widok z listą notatek
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


}
