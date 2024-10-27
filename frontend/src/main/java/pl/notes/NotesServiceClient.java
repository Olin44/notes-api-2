package pl.notes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "notes-service", url = "http://notes-service:8082/api/v1")
public interface NotesServiceClient {

    @GetMapping("/notes")
    NotePage getNotes(@RequestParam(value = "page", defaultValue = "0") Integer page,
                      @RequestParam(value = "size", defaultValue = "10") Integer size,
                      @RequestParam(value = "sort", defaultValue = "createdAt") String sort,
                      @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                      @RequestHeader("Authorization") String authorizationHeader);

    @GetMapping("/notes/{id}")
    Note getNoteById(@PathVariable("id") UUID id); // Pobierz notatkę do edycji

    @PutMapping("/notes/{id}")
    Note updateNote(@PathVariable("id") UUID id, @RequestBody NoteUpdateRequest noteUpdateRequest); // Aktualizacja notatki

    @DeleteMapping("/notes/{id}")
    void deleteNoteById(@PathVariable("id") UUID id);

    @PostMapping("/notes")
    void addNote(@RequestBody NoteCreateRequest noteCreateRequest);
}



