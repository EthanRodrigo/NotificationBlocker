package com.example.notificationblocker.ShowNotifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.notificationblocker.NotificationDB.Notific;
import com.example.notificationblocker.R;

import java.util.ArrayList;
import java.util.List;

public class ShowNotificationsRecViewAdapter extends RecyclerView.Adapter<ShowNotificationsRecViewAdapter.ViewHolder>{
    private final Context context;
    private List<Notific> notificationList = new ArrayList<>();

    public ShowNotificationsRecViewAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_notifications_recview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notific notification = notificationList.get(position);
        holder.notificationTitle.setText(notification.getContentTitle());
        holder.notificationText.setText(notification.getContentText());

        Glide.with(context)
                .asDrawable()
                .load(notification.getSmallIcon())
                .into(holder.notificationAppIcon);
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public void setNotificationList(List<Notific> notificationList) {
        this.notificationList = notificationList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView notificationAppIcon;
        TextView notificationText, notificationTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationText = itemView.findViewById(R.id.notificationText);
            notificationTitle = itemView.findViewById(R.id.notificationTitle);
            notificationAppIcon = itemView.findViewById(R.id.notificationAppIcon);
        }
    }
}
