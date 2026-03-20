package vod;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import vod.dto.GuildNoteDto;

@Component
public class GuildNoteValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return GuildNoteDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        GuildNoteDto dto = (GuildNoteDto) target;

        if (dto.getAuthor() == null || dto.getAuthor().isBlank()) {
            errors.rejectValue("author", "guildnote.author.missing");
        }
    }
}
