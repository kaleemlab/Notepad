package memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.listeners;

import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.Notification;

public interface NotificationListener {
    void onNotificationClicked(Notification notification, int position);
}
