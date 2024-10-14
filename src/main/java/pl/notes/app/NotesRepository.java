package pl.notes.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.notes.model.Note;

import java.util.UUID;

@Repository
public interface NotesRepository extends JpaRepository<NoteEntity, UUID> {
}
