package mylauncher.apps.esampaio.com.mylauncher.view.adapters.apps;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.support.annotation.LongDef;
import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import mylauncher.apps.esampaio.com.mylauncher.R;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.Application;
import mylauncher.apps.esampaio.com.mylauncher.core.preferences.AppPreferences;
import mylauncher.apps.esampaio.com.mylauncher.view.adapters.apps.AppsListAdapter;
import mylauncher.apps.esampaio.com.mylauncher.view.fragment.ApplicationListFragment;


public class AppsListPageAdapter extends FragmentPagerAdapter {
    public static final String TAG = AppsListPageAdapter.class.getCanonicalName();

    private Context context;
    private ArrayList<Application> applicationList;
    private int pagesCount = 0;
    private int appsPerPage = AppPreferences.getAppsNumberPerPage();

    public AppsListPageAdapter(FragmentManager fm, Context context, ArrayList<Application> applicationList) {
        super(fm);
        this.context = context;
        this.applicationList = applicationList;
        if (applicationList.size() < appsPerPage) {
            this.pagesCount = 1;
        } else {
            this.pagesCount = applicationList.size() / appsPerPage;
            if (applicationList.size() % appsPerPage != 0) {
                this.pagesCount++;
            }
        }

    }

    @Override
    public int getCount() {
        return pagesCount;
    }

    @Override
    public Fragment getItem(int position) {
        Log.d("AppsListPageAdapter","instantiating fragment at position: "+position);
        ArrayList<Application> applications = sublist(position);
        return ApplicationListFragment.newInstance(applications);
    }


    private ArrayList<Application> sublist(int pageNumber) {
        int min = appsPerPage * pageNumber;
        int max = min + appsPerPage;
        if (max >= applicationList.size()) {
            max = applicationList.size();
        }
        List<Application> sublist = applicationList.subList(min, max);
        ArrayList<Application> applications = new ArrayList<>();
        applications.addAll(sublist);
        return applications;
    }
}
