package com.example.notificationblocker.AppDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DBDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAnApp(App app);

    @Delete
    public void deleteAnApp(App app);

    @Query("SELECT * FROM BlockedApps")
    public List<App> getAllApps();  // get all apps

    @Query("SELECT appName FROM BlockedApps")
    public List<String> getAllAppNames();  // get app names
}
