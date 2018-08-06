package mylauncher.apps.esampaio.com.mylauncher.view.adapters.apps;

import android.content.Context;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mylauncher.apps.esampaio.com.mylauncher.core.entities.InstalledApplication;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.Launchable;
import mylauncher.apps.esampaio.com.mylauncher.core.preferences.AppPreferences;
import mylauncher.apps.esampaio.com.mylauncher.view.fragment.ApplicationListFragment;


public class AppsListPageAdapter extends FragmentPagerAdapter {
    public static final String TAG = AppsListPageAdapter.class.getCanonicalName();

    private Context context;
    private ArrayList<Launchable> applicationList;
    private int pagesCount = 0;
    private int appsPerPage = AppPreferences.getAppsNumberPerPage();

    public AppsListPageAdapter(FragmentManager fm, Context context, ArrayList<Launchable> applicationList) {
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
        ArrayList<Launchable> applications = sublist(position);
        return ApplicationListFragment.newInstance(applications);
    }


    private ArrayList<Launchable> sublist(int pageNumber) {
        int min = appsPerPage * pageNumber;
        int max = min + appsPerPage;
        if (max >= applicationList.size()) {
            max = applicationList.size();
        }
        List<Launchable> sublist = applicationList.subList(min, max);
        ArrayList<Launchable> applications = new ArrayList<>();
        applications.addAll(sublist);
        return applications;
    }
}
