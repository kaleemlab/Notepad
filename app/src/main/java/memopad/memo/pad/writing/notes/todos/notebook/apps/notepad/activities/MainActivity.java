package memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.activities;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.codemybrainsout.ratingdialog.RatingDialog;
import com.google.android.material.navigation.NavigationView;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.R;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.fragments.ArchiveFragment;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.fragments.HomeFragment;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.fragments.RemindersFragment;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.fragments.TrashFragment;
import memopad.memo.pad.writing.notes.todos.notebook.apps.notepad.utils.Helper;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Drawer Layout
    private DrawerLayout drawer;

    // toolbar buttons
    public Button extraAction;
    public ImageView moreOptions;
    public TextView toolbarTitle;

    // toolbar selector
    public RelativeLayout toolbarSelector;
    public ImageView toolbarSelectorClose;
    public ImageView toolbarSelectorDeleteNotes;
    public TextView toolbarSelectorSelectedItems;


    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private int total_launch;

    private final static int DAYS_UNTIL_PROMPT = 3;//Min number of days
    private final static int LAUNCHES_UNTIL_PROMPT = 5;//Min number of launches


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // OPTIONS
        Helper.dark_mode(this);
        Helper.fullscreen_mode(this);
        Helper.screen_state(this);

        setContentView(R.layout.activity_main);

        // initialize drawer
        drawer = findViewById(R.id.drawer_layout);

        // navigation view (navigation drawer)
        NavigationView navigationView = findViewById(R.id.navigation_drawer);
        navigationView.setNavigationItemSelectedListener(this);
        // hide menu widget if android version is below oreo
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.O) {
            navigationView.getMenu().findItem(R.id.menu_widget).setVisible(false);
        }

        // rating dialog
        prefs = getPreferences(Context.MODE_PRIVATE);
        editor = prefs.edit();
        total_launch = prefs.getInt("counter", 0);
        total_launch++;
        editor.putInt("counter", total_launch);
        editor.commit();


        // more options
        moreOptions = findViewById(R.id.more_options);

        // toolbar title
        toolbarTitle = findViewById(R.id.toolbar_title);

        // toolbar selector
        toolbarSelector = findViewById(R.id.toolbar_selector);
        toolbarSelectorClose = findViewById(R.id.go_back);
        toolbarSelectorDeleteNotes = findViewById(R.id.delete_note);
        toolbarSelectorSelectedItems = findViewById(R.id.selected_items);

        // toggle navigation drawer
        findViewById(R.id.open_navigation_drawer).setOnClickListener(v -> drawer.openDrawer(Gravity.START));

        if (savedInstanceState == null) {
            if (getIntent().getStringExtra("fragment") != null) {
                if (Objects.equals(getIntent().getStringExtra("fragment"), "reminders")) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RemindersFragment()).commit();
                    navigationView.setCheckedItem(R.id.menu_reminders);
                } else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                    navigationView.setCheckedItem(R.id.menu_home);
                }
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                navigationView.setCheckedItem(R.id.menu_home);
            }
        }


        // rating dialog

        SharedPreferences prefs = getSharedPreferences("apprater", 0);
        if (prefs.getBoolean("dontshowagain", false)) {
            return;
        }
        SharedPreferences.Editor editor = prefs.edit();

        // Increment launch counter
        long launch_count = prefs.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);

        // Get date of first launch
        Long date_firstLaunch = prefs.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }

        // Wait at least n days before opening
        if (launch_count >= LAUNCHES_UNTIL_PROMPT) {
            if (System.currentTimeMillis() >= date_firstLaunch +
                    (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {

                final RatingDialog ratingDialog = new RatingDialog.Builder(this)
                        .threshold(5)
                        .session(7)
                        .negativeButtonText("Never")
                        .playstoreUrl("market://details?id=" + getPackageName())
                        .session(1)
                        .onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener() {
                            @Override
                            public void onFormSubmitted(String feedback) {

                            }
                        }).build();
                ratingDialog.show();

            }
        }

        editor.apply();


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_trash) {
            findViewById(R.id.extra_action).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.extra_action).setVisibility(View.GONE);
        }

        if (item.getItemId() == R.id.menu_home) {
            findViewById(R.id.more_options).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.more_options).setVisibility(View.GONE);
        }

        switch (item.getItemId()) {
            case R.id.menu_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
                break;
            case R.id.menu_reminders:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RemindersFragment()).commit();
                break;
            case R.id.menu_archive:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ArchiveFragment()).commit();
                break;
            case R.id.menu_trash:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TrashFragment()).commit();
                enableDeleteAll();
                break;
            case R.id.menu_notification:
                startActivity(new Intent(MainActivity.this, NotificationsActivity.class));
                break;
            case R.id.menu_widget:
                startActivity(new Intent(MainActivity.this, WidgetActivity.class));
                break;
            case R.id.menu_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                finish();
                break;
            case R.id.menu_rate:
                rateApp();
                break;
            case R.id.menu_about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                break;
        }

        toolbarTitle.setText(getString(R.string.app_name));
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * rate application by opening the link
     * of the app on the Google Play Store
     */
    private void rateApp() {
        /* get package name */
        String appPackageName = getApplicationContext().getPackageName();

        /* handle link of the Google Play Store */
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (ActivityNotFoundException errorException) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    /**
     * add delete all trash notes
     */
    private void enableDeleteAll() {
        extraAction = findViewById(R.id.extra_action);
        extraAction.setVisibility(View.VISIBLE);
        extraAction.setText(getString(R.string.delete_all));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
//        else {
//            GoogleAdmobNativeAdBottomSheetModal googleAdmobNativeAdBottomSheetModal = new GoogleAdmobNativeAdBottomSheetModal();
//            googleAdmobNativeAdBottomSheetModal.show(getSupportFragmentManager(), "TAG");
//        }
    }
}
