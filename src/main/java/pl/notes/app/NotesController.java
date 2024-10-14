package pl.notes.app;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.notes.api.NotesApi;
import pl.notes.model.Note;
import pl.notes.model.NoteCreateRequest;
import pl.notes.model.NotePage;
import pl.notes.model.NoteUpdateRequest;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class NotesController implements NotesApi {

    private final NotesService notesService;

    @Override
    public ResponseEntity<NotePage> notesGet(Integer page, Integer size, String sort, String direction) {
        NotePage response = notesService.notesGet(page, size, sort, direction);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> notesIdDelete(UUID id) {
        notesService.notesIdDelete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Note> notesIdGet(UUID id) {
        Note response = notesService.notesIdGet(id);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Note> notesIdPut(UUID id, NoteUpdateRequest noteUpdateRequest) {
        Note response = notesService.notesIdPut(id, noteUpdateRequest);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Note> notesPost(NoteCreateRequest noteCreateRequest) {
        Note response = notesService.notesPost(noteCreateRequest);
        return ResponseEntity.ok(response);
    }
}