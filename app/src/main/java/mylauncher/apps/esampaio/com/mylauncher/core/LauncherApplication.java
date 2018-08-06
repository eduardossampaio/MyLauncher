package mylauncher.apps.esampaio.com.mylauncher.core;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

public class LauncherApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        LauncherApplication.context = this;
    }

    public static Context getAppContext() {
        return LauncherApplication.context;
    }
}
