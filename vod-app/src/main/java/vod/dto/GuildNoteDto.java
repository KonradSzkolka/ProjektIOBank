package vod.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GuildNoteDto {

    @NotNull
    @Size(min = 2, max = 20)
    private String title;

    private String content;

    @NotNull
    private String author; // np. konto gracza

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
