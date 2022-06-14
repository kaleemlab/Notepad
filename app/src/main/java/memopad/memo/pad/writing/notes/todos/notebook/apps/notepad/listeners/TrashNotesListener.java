package memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.listeners;

import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.TrashNote;

public interface TrashNotesListener {
    void onNoteClicked(TrashNote trashNote, int position);

    void onNoteLongClicked(TrashNote trashNote, int position);
}
