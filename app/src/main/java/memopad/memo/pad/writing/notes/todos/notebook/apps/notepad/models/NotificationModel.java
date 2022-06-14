package memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.models;

import java.io.Serializable;

public class NotificationModel implements Serializable {

    public Long id;
    public String title;
    public String content;
    public Long obj_id;

    public Boolean read = false;
    public Long created_at;
}
