package vod.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Note {

    private Long id;

    @NotNull
    @Size(min = 2, max = 20)
    private String title;

    private String content;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}
