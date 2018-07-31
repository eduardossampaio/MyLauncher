package mylauncher.apps.esampaio.com.mylauncher;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AppsListAdapter extends RecyclerView.Adapter<AppsListAdapter.AppsListPageAdapterViewHolder> {

    private Context context;

    public AppsListAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public AppsListPageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflated = LayoutInflater.from(context).inflate(R.layout.app_recycler_view_item, null, false);
        return new AppsListPageAdapterViewHolder(inflated);
    }

    @Override
    public void onBindViewHolder(@NonNull AppsListPageAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    static  class AppsListPageAdapterViewHolder extends RecyclerView.ViewHolder{

        public AppsListPageAdapterViewHolder(View itemView) {
            super(itemView);
        }
    }
}
