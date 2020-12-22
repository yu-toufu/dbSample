package com.example.dbapp;

import android.os.Bundle;

import com.example.dbapp.db.User;
import com.example.dbapp.db.UserDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private LoggerViewModel mModel;

    Logger logger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        logger = new Logger(new Logger.Callback() {
            @Override
            public void onNew(String object) {
                mModel.setAll(logger.getAll());
            }
        });
        mModel.setAll(logger.getAll());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void insert(View view){
        final UserDao dao = DBApplication.db.userDao();
        Executor myExecutor = Executors.newSingleThreadExecutor();
        Random r = new Random();
        myExecutor.execute(()->{
            User user = new User();
            user.firstName="hoge";
            user.lastName="piyo";
            user.uid = r.nextInt();
            dao.insertAll(user);
            Log.i("MainActivity","======== insert =========");
        });
    }

    public void getAllUsers(View view){
        Log.i("MainActivity","======== getAllUsers =========");
        final UserDao dao = DBApplication.db.userDao();
        Executor myExecutor = Executors.newSingleThreadExecutor();
        myExecutor.execute(()->{
            List<User> users = dao.getAll();
            for (User user : users){
                Log.i("MainActivity",String.valueOf(user.uid));
            }
        });
    }

}