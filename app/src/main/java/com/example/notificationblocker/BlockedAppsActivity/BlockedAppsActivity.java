package com.example.notificationblocker.BlockedAppsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.example.notificationblocker.R;

import com.example.notificationblocker.AppDB.App;
import com.example.notificationblocker.AppDB.AppsDB;

import java.util.List;

public class BlockedAppsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocked_apps);

        RecyclerView blockedListRecView = findViewById(R.id.blockedApps);

        AppsDB db = AppsDB.getInstance(this);
        List<App> blockedAppList = db.dbDAO().getAllApps();

        BlockedAppsRecViewAdapter blockedAppsRecViewAdapter = new BlockedAppsRecViewAdapter(this);
        blockedAppsRecViewAdapter.setBlockedAppList(blockedAppList);

        blockedListRecView.setAdapter(blockedAppsRecViewAdapter);
        blockedListRecView.setLayoutManager(new LinearLayoutManager(this));
    }
}