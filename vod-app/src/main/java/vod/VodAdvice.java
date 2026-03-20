package vod;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class VodAdvice {

    private final NoteTitleUniqueValidator noteTitleUniqueValidator;
    private final GuildNoteValidator guildNoteValidator;

    public VodAdvice(NoteTitleUniqueValidator noteTitleUniqueValidator,
                     GuildNoteValidator guildNoteValidator) {
        this.noteTitleUniqueValidator = noteTitleUniqueValidator;
        this.guildNoteValidator = guildNoteValidator;
    }

    @InitBinder("note")
    void initNoteBinder(WebDataBinder binder) {
        binder.addValidators(noteTitleUniqueValidator);
    }

    @InitBinder("guildNoteDto")
    void initGuildNoteBinder(WebDataBinder binder) {
        binder.addValidators(guildNoteValidator);
    }

    // globalny handler wyjątków (odpowiednik handlera z labu)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Illegal argument: " + ex.getMessage());
    }
}
