package mylauncher.apps.esampaio.com.mylauncher.core.packages;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

import mylauncher.apps.esampaio.com.mylauncher.core.entities.Application;

public class PackageUtils {

    public static ArrayList<Application> getInstalledPackages(Context context){
        Intent launchIntent = new Intent(Intent.ACTION_MAIN,null);
        launchIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> packages = packageManager.queryIntentActivities(launchIntent, 0);
        ArrayList<Application> applications = new ArrayList<>();
        for (ResolveInfo resolveInfo: packages) {
            Application application = new Application();
            application.load(resolveInfo,packageManager);
            applications.add(application);
        }
        return applications;

    }
}
