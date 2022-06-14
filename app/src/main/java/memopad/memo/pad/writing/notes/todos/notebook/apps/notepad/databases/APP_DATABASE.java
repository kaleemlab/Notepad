package memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.databases;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.ArchiveNote;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.Category;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.Note;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.Notification;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.Todo;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.TodosList;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.entities.TrashNote;

@Database(
        entities = {
                Note.class,
                ArchiveNote.class,
                Category.class,
                Notification.class,
                TrashNote.class,
                Todo.class,
                TodosList.class
        },
        version = 2,
        exportSchema = false)
public abstract class APP_DATABASE extends RoomDatabase {

    public abstract DAO dao();

    private static APP_DATABASE appDatabase;

    public static synchronized APP_DATABASE requestDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context, APP_DATABASE.class, "noted_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }

    public static void destroyDatabase() {
        appDatabase = null;
    }
}
