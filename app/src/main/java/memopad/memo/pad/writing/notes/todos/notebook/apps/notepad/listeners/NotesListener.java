package memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.listeners;

import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.Note;

public interface NotesListener {
    void onNoteClicked(Note note, int position);

    void onNoteLongClicked(Note note, int position);
}
