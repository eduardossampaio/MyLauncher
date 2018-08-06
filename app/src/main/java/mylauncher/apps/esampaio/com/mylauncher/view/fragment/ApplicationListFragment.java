package mylauncher.apps.esampaio.com.mylauncher.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


import mylauncher.apps.esampaio.com.mylauncher.R;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.InstalledApplication;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.Launchable;
import mylauncher.apps.esampaio.com.mylauncher.view.adapters.apps.AppsListAdapter;
import mylauncher.apps.esampaio.com.mylauncher.view.custom.ItemOffsetDecoration;
import mylauncher.apps.esampaio.com.mylauncher.view.custom.SpaceItemDecoration;
import mylauncher.apps.esampaio.com.mylauncher.view.custom.SpanningGridLayoutManager;

public class ApplicationListFragment extends Fragment {
    public static final String TAG = "ApplicationListFragment";
    public static final String EXTRAS_APPLICATION_LIST = "APPLICATIONS_LIST";

    public static ApplicationListFragment newInstance(ArrayList<Launchable> applications) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRAS_APPLICATION_LIST,applications);
        ApplicationListFragment fragment = new ApplicationListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "instantiateItem");
        ArrayList<Launchable> applicationList = (ArrayList<Launchable>) getArguments().getSerializable(EXTRAS_APPLICATION_LIST);
        View baseLayout = inflater.inflate(R.layout.apps_page,container,false);
        RecyclerView appsList = baseLayout.findViewById(R.id.apps_list);
        appsList.setAdapter(new AppsListAdapter(this.getActivity(),applicationList));
        GridLayoutManager gridLayoutManager = new SpanningGridLayoutManager(this.getActivity(), 4);
//        appsList.addItemDecoration(new SpaceItemDecoration(30,false,false));
        appsList.setLayoutManager(gridLayoutManager);

        gridLayoutManager.setItemPrefetchEnabled(true);
        return baseLayout;
    }

}
