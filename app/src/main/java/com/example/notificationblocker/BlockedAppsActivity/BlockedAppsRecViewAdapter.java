package com.example.notificationblocker.BlockedAppsActivity;

import com.bumptech.glide.Glide;
import com.example.notificationblocker.AppDB.App;
import com.example.notificationblocker.BitmapUtility;
import com.example.notificationblocker.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class BlockedAppsRecViewAdapter extends RecyclerView.Adapter<BlockedAppsRecViewAdapter.ViewHolder>{
    private Context context;
    private List<App> blockedAppList = new ArrayList<>();

    public BlockedAppsRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blocked_apps_recview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final App blockedApp = blockedAppList.get(position);

        holder.appName.setText(blockedApp.getAppName());

        Glide.with(context)
                .asDrawable()
                .load(BitmapUtility.getImage(blockedAppList.get(position).getAppIcon()))
                .into(holder.appIcon);
    }

    @Override
    public int getItemCount() {
        return blockedAppList.size();
    }

    public void setBlockedAppList(List<App> blockedAppList) {
        this.blockedAppList = blockedAppList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView appName;
        private ImageView appIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = itemView.findViewById(R.id.blockedAppName);
            appIcon = itemView.findViewById(R.id.blockedAppIcon);
        }
    }
}
