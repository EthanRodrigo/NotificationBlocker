package com.example.notificationblocker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.notificationblocker.AppDB.App;
import com.example.notificationblocker.AppDB.AppsDB;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

public class AppListRecViewAdapter extends RecyclerView.Adapter<AppListRecViewAdapter.ViewHolder>{
    Context context;
    private List<App> appList = new ArrayList<>();  // List to store the provided app list

    public AppListRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_list_recview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final App app = appList.get(position);  // the app from the list, which has all the installed apps

        holder.appName.setText(appList.get(position).getAppName());

        // setting up the appIcon
        Glide.with(context)
                .asDrawable()
                .load(appList.get(position).getAppIcon())
                .into(holder.appIcon);

        AppsDB db = AppsDB.getInstance(context);  // The db

        List<String> blockedList = db.dbDAO().getAllAppNames(); // the name list of the blocked apps
        if(blockedList.contains(app.getAppName())){  // if the blocked list has the current app on the holder
            app.setSelected(true); // make the app is selected
        }

        holder.appStatus.setOnCheckedChangeListener(null);
        holder.appStatus.setChecked(app.isSelected());  // switch on or off according to whether the app is selected or not

        holder.appStatus.setOnCheckedChangeListener((buttonView, isChecked) -> {
            app.setSelected(isChecked);  // app needs to be selected if it's checked

            if(app.isSelected()){  // if the app is selected
                db.dbDAO().insertAnApp(new App(app.getAppName(), app.getAppIcon(), app.isSelected()));  // add it into the db
            }else {
                db.dbDAO().deleteAnApp(new App(app.getAppName(), app.getAppIcon(), app.isSelected())); // else delete it from the db
            }
        });
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    public void setAppList(List<App> appList) {
        this.appList = appList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView appName;
        private ImageView appIcon;
        private SwitchMaterial appStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appName = (TextView) itemView.findViewById(R.id.appName);
            appIcon = (ImageView) itemView.findViewById(R.id.appIcon);
            appStatus = (SwitchMaterial) itemView.findViewById(R.id.appStatus);
        }
    }
}
