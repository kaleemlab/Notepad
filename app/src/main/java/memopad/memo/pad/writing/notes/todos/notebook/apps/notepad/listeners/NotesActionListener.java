package memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.listeners;

import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.Note;

public interface NotesActionListener {
    void onNoteAction(Note note, int position, boolean isSelected);
}
