package mylauncher.apps.esampaio.com.mylauncher.view.adapters.apps;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import mylauncher.apps.esampaio.com.mylauncher.R;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.Application;

public class AppsListAdapter extends RecyclerView.Adapter<AppsListAdapter.AppsListPageAdapterViewHolder> {

    private Context context;
    private List<Application> applications;

    public AppsListAdapter(Context context,List<Application> applications){
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
        holder.bind(applications.get(position));
    }

    @Override
    public int getItemCount() {
        return applications == null ? 0 : applications.size();
    }

    static  class AppsListPageAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView appText;
        private ImageView appIcon;
        public AppsListPageAdapterViewHolder(View itemView) {
            super(itemView);
            this.appText = itemView.findViewById(R.id.app_name);
            this.appIcon = itemView.findViewById(R.id.app_icon);
        }

        private void bind(Application application){
            this.appIcon.setImageDrawable(application.getApplicationIcon());
            this.appText.setText(application.getApplicationName());
        }
    }
}
