package memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.listeners;

import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.Todo;

public interface TodosListener {
    void onTodoClicked(Todo todo, int position);

    void onTodoLongClicked(Todo todo, int position);

    void onTodoStateCLicked(Todo todo, int position, boolean checked);
}
