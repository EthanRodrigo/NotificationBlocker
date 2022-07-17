package com.example.notificationblocker.NotificationDB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotificDBDAO{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertANotific(Notific notific);

    @Query("DELETE FROM Notifications")
    public void deleteAllNotifics();

    @Query("SELECT * FROM Notifications")
    public List<Notific> getAllNotifics();

    @Query("SELECT id FROM Notifications ")
    public List<Integer> getAllIds();

    @Query("SELECT * FROM Notifications WHERE id IS :notificId")
    public Notific getANotification(int notificId);
}
