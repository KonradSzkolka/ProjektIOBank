package vod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import vod.model.Note;
import vod.service.NoteService;

import java.util.Locale;

@RestController
@RequestMapping("/webapi")
public class NoteRest {

    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;
    private final NoteService noteService;
    private final NoteTitleUniqueValidator validator;

    public NoteRest(MessageSource messageSource,
                    LocaleResolver localeResolver,
                    NoteService noteService,
                    NoteTitleUniqueValidator validator) {
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
        this.noteService = noteService;
        this.validator = validator;
    }

    @InitBinder
    void initBinder(WebDataBinder binder) {
        binder.addValidators(validator); // nie setValidator!
    }

    @PostMapping("/notes")
    public ResponseEntity<?> addNote(
            @Valid @RequestBody Note note,
            Errors errors,
            HttpServletRequest request) {

        if (errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);

            String errorMessage = errors.getAllErrors().stream()
                    .map(oe -> messageSource.getMessage(oe.getCode(), new Object[0], locale))
                    .reduce("errors:\n", (accu, msg) -> accu + msg + "\n");

            return ResponseEntity.badRequest().body(errorMessage);
        }

        note = noteService.addNote(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }
}
