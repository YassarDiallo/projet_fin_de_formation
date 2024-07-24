package orange.odc.sprintboot.repository;

import orange.odc.sprintboot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import orange.odc.sprintboot.models.Note;

import java.util.List;

public interface NoteRepository  extends JpaRepository<Note, Long>{


}
