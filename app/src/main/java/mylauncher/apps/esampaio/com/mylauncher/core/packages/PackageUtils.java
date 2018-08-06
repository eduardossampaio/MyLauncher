package mylauncher.apps.esampaio.com.mylauncher.core.packages;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

import mylauncher.apps.esampaio.com.mylauncher.R;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.InstalledApplication;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.Launchable;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.LaunchableActivity;
import mylauncher.apps.esampaio.com.mylauncher.view.activities.SettingsActivity;

public class PackageUtils {


    public static ArrayList<Launchable> getLaunchables(Context context){
        ArrayList<Launchable> launchables = new ArrayList<>();
        launchables.addAll(getInstalledPackages(context));
        launchables.addAll(getLaunchableActivities());
        return launchables;
    }
    public static ArrayList<InstalledApplication> getInstalledPackages(Context context){
        Intent launchIntent = new Intent(Intent.ACTION_MAIN,null);
        launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> packages = packageManager.queryIntentActivities(launchIntent, 0);
        ArrayList<InstalledApplication> applications = new ArrayList<>();
        for (ResolveInfo resolveInfo: packages) {
            InstalledApplication application = new InstalledApplication();
            application.load(resolveInfo,packageManager);
            applications.add(application);
        }
        return applications;
    }

    public static ArrayList<LaunchableActivity> getLaunchableActivities(){
        ArrayList<LaunchableActivity> launchableActivities = new ArrayList<>();
        launchableActivities.add(new LaunchableActivity("Configurações", R.mipmap.ic_launcher, SettingsActivity.class));
        return launchableActivities;
    }
}
