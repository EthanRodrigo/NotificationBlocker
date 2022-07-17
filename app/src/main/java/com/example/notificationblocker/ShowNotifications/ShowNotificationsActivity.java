package com.example.notificationblocker.ShowNotifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.notificationblocker.AppDB.App;
import com.example.notificationblocker.AppDB.AppsDB;
import com.example.notificationblocker.NotificationDB.Notific;
import com.example.notificationblocker.NotificationDB.NotificDB;
import com.example.notificationblocker.NotificationDB.NotificDBDAO;
import com.example.notificationblocker.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShowNotificationsActivity extends AppCompatActivity {
    private final NotificDB notificDB = NotificDB.getInstance(this);
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notifications);

        RecyclerView recView = findViewById(R.id.notificationList);
        ShowNotificationsRecViewAdapter adapter = new ShowNotificationsRecViewAdapter(context);
        adapter.setNotificationList(notificDB.dbDAO().getAllNotifics());
        recView.setAdapter(adapter);
        recView.setLayoutManager(new LinearLayoutManager(context));
    }
    public void clearNotifications(View view) {
        notificDB.dbDAO().deleteAllNotifics();
    }
}