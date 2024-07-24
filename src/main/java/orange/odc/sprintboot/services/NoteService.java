package orange.odc.sprintboot.services;

import jakarta.transaction.Transactional;
import orange.odc.sprintboot.models.Note;
import orange.odc.sprintboot.models.User;
import orange.odc.sprintboot.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserService userService;

    /*public List<Note> getNotesByUserId(int userId) {
        return noteRepository.findByUserId(userId);
    }*/

    public List<Note> getAll(){
        return noteRepository.findAll();
    }



    public Note saveNote(Note note) {

        return noteRepository.save(note);
    }

    /*@Transactional
    public Note saveNoteUser(Note note, User user) {
        note.setUser(user);
        user.getNotes().add(note);
        return noteRepository.save(note);
    }*/


    public Note getNote(Long id) {return noteRepository.findById(id).get();
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

}
