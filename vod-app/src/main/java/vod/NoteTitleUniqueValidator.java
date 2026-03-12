package vod;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vod.model.Note;
import vod.service.NoteService;

@Component
public class NoteTitleUniqueValidator implements Validator {

    private final NoteService noteService;

    public NoteTitleUniqueValidator(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Note.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Note validatedNote = (Note) target;

        if (validatedNote.getTitle() == null) {
            return; // zostaw to wbudowanym walidatorom NotNull/Size
        }

        boolean duplicated = noteService.getAllNotes().stream()
                .anyMatch(note -> note.getTitle().equalsIgnoreCase(validatedNote.getTitle()));

        if (duplicated) {
            errors.rejectValue("title", "note.title.duplicated");
        }
    }
}
