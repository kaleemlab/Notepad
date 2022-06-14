package memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.listeners;

import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.TodosList;

public interface TodoListListener {
    void onTodoListClicked(TodosList todosList, int position);
}
