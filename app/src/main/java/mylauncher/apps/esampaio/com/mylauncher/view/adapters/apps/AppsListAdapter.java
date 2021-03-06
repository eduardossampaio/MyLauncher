package mylauncher.apps.esampaio.com.mylauncher.view.adapters.apps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mylauncher.apps.esampaio.com.mylauncher.R;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.InstalledApplication;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.Launchable;
import mylauncher.apps.esampaio.com.mylauncher.core.preferences.AppPreferences;

public class AppsListAdapter extends RecyclerView.Adapter<AppsListAdapter.AppsListPageAdapterViewHolder> {

    private Context context;
    private List<Launchable> applications;

    public AppsListAdapter(Context context,List<Launchable> applications){
        this.context = context;
        this.applications = applications;
    }


    @NonNull
    @Override
    public AppsListPageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflated = LayoutInflater.from(context).inflate(R.layout.app_recycler_view_item, null, false);
        return new AppsListPageAdapterViewHolder(inflated);
    }

    @Override
    public void onBindViewHolder(@NonNull AppsListPageAdapterViewHolder holder, int position) {
        if(position<applications.size()) {
            holder.bind(context, applications.get(position));
        }else {
            holder.bind(context,null);
        }
    }

    @Override
    public int getItemCount() {
        return AppPreferences.getAppsNumberPerPage();
        //return applications == null ? 0 : applications.size();
    }

    static  class AppsListPageAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView appText;
        private ImageView appIcon;
        public AppsListPageAdapterViewHolder(View itemView) {
            super(itemView);
            this.appText = itemView.findViewById(R.id.app_name);
            this.appIcon = itemView.findViewById(R.id.app_icon);
        }

        private void bind(final Context context,final Launchable application){
            if(application == null){
                this.itemView.setVisibility(View.INVISIBLE);
                return;
            }
            this.itemView.setVisibility(View.VISIBLE);
            this.appIcon.setImageDrawable(application.getApplicationIcon(context));
            this.appText.setText(application.getApplicationName());
            this.appIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    application.launch(context,null);
                }
            });
        }
    }
}
