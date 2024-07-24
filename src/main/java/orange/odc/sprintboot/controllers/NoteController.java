package orange.odc.sprintboot.controllers;

import orange.odc.sprintboot.models.Note;
import orange.odc.sprintboot.services.NoteService;
import orange.odc.sprintboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;


    @RequestMapping("/listeNotes")
    public String afficherListeNote(Model m) {
        m.addAttribute("notes", noteService.getAll());
        return "note/listeNote";
    }

    /*@GetMapping("/User/{userId}")
    public List<Note> getNotesByUserId(@PathVariable Long userId){
        return noteService.getNotesByUserId(Math.toIntExact(userId));
    }*/

    @RequestMapping("/ajouterNote")
    public String ajouterNote(Model model) {
        model.addAttribute("note", new Note());
        return "note/noteFrom";
    }



    @RequestMapping("/accueil")
    public String index(Model model){
        model.addAttribute("note", new Note());
        return "note/listeNote";
    }

    @RequestMapping(value = "/saveNote", method = RequestMethod.POST)
    public String saveNote(@ModelAttribute Note note) {
        System.out.println("******************* Enregistrement************************");
        noteService.saveNote(note);

        System.out.println(note.getDescription()+" "+note.getTitle());
        return "redirect:/listeNotes";
    }

    @RequestMapping("/editeNote/{id}")
    public ModelAndView editNotes(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("note/editNote");
        mav.addObject("noteM", noteService.getNote(id));
        return mav;
    }

    @RequestMapping("deleteNote/{id}")
    public String deletNote(@PathVariable(name = "id") Long id) {
        noteService.deleteNote(id);
        return "redirect:/listeNotes";
    }

}
