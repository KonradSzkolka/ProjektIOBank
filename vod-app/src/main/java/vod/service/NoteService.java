package vod.service;

import org.springframework.stereotype.Service;
import vod.model.Note;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NoteService {

    private final List<Note> notes = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Note> getAllNotes() {
        return Collections.unmodifiableList(notes);
    }

    public Note addNote(Note note) {
        note.setId(idGenerator.getAndIncrement());
        notes.add(note);
        return note;
    }
}
