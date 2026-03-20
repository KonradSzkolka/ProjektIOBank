package vod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import vod.dto.GuildNoteDto;

import java.util.Locale;

@RestController
@RequestMapping("/webapi")
public class GuildNoteRest {

    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    public GuildNoteRest(MessageSource messageSource, LocaleResolver localeResolver) {
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
    }

    // przykład z PDF: specjalny parametr powoduje wyjątek, który łapie ControllerAdvice
    @GetMapping("/guild-notes")
    public ResponseEntity<String> getNotes(@RequestParam(required = false) String phrase) {
        if ("foo".equalsIgnoreCase(phrase)) {
            throw new IllegalArgumentException("phrase 'foo' is not allowed");
        }
        return ResponseEntity.ok("notes list placeholder");
    }

    @PostMapping("/guild-notes")
    public ResponseEntity<?> addGuildNote(
            @Valid @RequestBody GuildNoteDto guildNoteDto,
            Errors errors,
            HttpServletRequest request) {

        if (errors.hasErrors()) {
            Locale locale = localeResolver.resolveLocale(request);

            String errorMessage = errors.getAllErrors().stream()
                    .map(oe -> messageSource.getMessage(oe.getCode(), new Object[0], locale))
                    .reduce("errors:\n", (accu, msg) -> accu + msg + "\n");

            return ResponseEntity.badRequest().body(errorMessage);
        }

        // tu normalnie byłby zapis w serwisie; na labie echo DTO
        return ResponseEntity.status(HttpStatus.CREATED).body(guildNoteDto);
    }
}
