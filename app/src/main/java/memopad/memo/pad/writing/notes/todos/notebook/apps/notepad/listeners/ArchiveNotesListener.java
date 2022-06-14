package memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.listeners;

import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.ArchiveNote;

public interface ArchiveNotesListener {
    void onNoteClicked(ArchiveNote archive_note, int position);

    void onNoteLongClicked(ArchiveNote archive_note, int position);
}
