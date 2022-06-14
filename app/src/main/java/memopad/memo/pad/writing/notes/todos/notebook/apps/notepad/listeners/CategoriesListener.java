package memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.listeners;

import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.Category;

public interface CategoriesListener {
    void onCategoryDeleteClicked(Category category, int position);

    void onCategoryEditClicked(Category category, int position);
}
