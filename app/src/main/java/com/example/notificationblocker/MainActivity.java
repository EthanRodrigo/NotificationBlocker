package com.example.notificationblocker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.notificationblocker.AppDB.App;
import com.example.notificationblocker.BlockedAppsActivity.BlockedAppsActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private List<App> apps = new ArrayList<>();  // List of installed apps
    public static boolean BLOCK_ALL = false;  // A indicator to know whether all the apps are blocked are not

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handling of the recycler view
        RecyclerView appListRecView = findViewById(R.id.appList);
        getAllTheApps();

        AppListRecViewAdapter appListAdapter = new AppListRecViewAdapter(this);
        appListAdapter.setAppList(apps);

        appListRecView.setAdapter(appListAdapter);
        appListRecView.setLayoutManager(new LinearLayoutManager(this));


        // Blocking of notifications
        if(!isNotificationAccessGiven()){  // if notification access is not granted
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");  // open settings to grant
            startActivity(intent);
        }

        SwitchMaterial blockAllNotifications = findViewById(R.id.blockAllNotifications);
        blockAllNotifications.setChecked(BLOCK_ALL);
        blockAllNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            BLOCK_ALL = isChecked;
        });

    }

    /***
     * getAllTheApps: To get the installed apps in the system.
     */
    private void getAllTheApps() {
        List<ApplicationInfo> appList = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
        for (ApplicationInfo appInfo : appList) {
            if (getPackageManager().getLaunchIntentForPackage(appInfo.packageName) != null) {  // get the app only if it has a launcher
                apps.add(new App((String) appInfo.loadLabel(getPackageManager()), BitmapUtility.getBytes(appInfo.loadIcon(getPackageManager())), false));
            }
        }
    }

    /***
     * goToBlockedApps: Start the new activity to display the list of blocked apps.
     * @param view
     * This is the function that is called by the button in activity_main.xml
     */
    public void goToBlockedApps(View view){
        Intent intent = new Intent(this, BlockedAppsActivity.class);
        startActivity(intent);
    }

    /***
     * isNotificationAccessGiven: Checking whether the user has granted the notification access or not
     * @return bool accoring to the check
     */
    private boolean isNotificationAccessGiven() {
        try {
            Set<String> enabledListenerPackagesSet = NotificationManagerCompat.getEnabledListenerPackages(this);  // get a list of packages that has access to the notifications
            for (String string : enabledListenerPackagesSet)  // if this apps is in the list
                if (string.contains(getPackageName())) return true; // the notification access has granted
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
        return false;
    }
}