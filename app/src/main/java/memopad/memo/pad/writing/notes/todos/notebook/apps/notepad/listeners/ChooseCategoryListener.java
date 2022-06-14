package memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.listeners;

import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.Category;

public interface ChooseCategoryListener {
    void onCategoryClicked(Category category, int position);
}
