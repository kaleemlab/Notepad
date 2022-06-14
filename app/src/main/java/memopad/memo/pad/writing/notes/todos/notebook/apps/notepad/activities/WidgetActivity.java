package memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.R;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.utils.Helper;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.widgets.DefaultAppWidget;

public class WidgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // OPTIONS
        Helper.dark_mode(this);
        Helper.fullscreen_mode(this);
        Helper.screen_state(this);

        setContentView(R.layout.activity_widget);

        // go back
        findViewById(R.id.go_back).setOnClickListener(v -> finish());

        // select widget one
        findViewById(R.id.widget_one).setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                AppWidgetManager appWidgetManager = getSystemService(AppWidgetManager.class);
                ComponentName provider = new ComponentName(this, DefaultAppWidget.class);

                Bundle bundle = new Bundle();
                bundle.putString("request", "widget_default");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (appWidgetManager.isRequestPinAppWidgetSupported()) {
                        Intent callback = new Intent(this, DefaultAppWidget.class);
                        PendingIntent success_callback = PendingIntent.getBroadcast(WidgetActivity.this, 0, callback, PendingIntent.FLAG_IMMUTABLE);

                        appWidgetManager.requestPinAppWidget(provider, bundle, success_callback);
                    }
                }
            }

        });
    }
}