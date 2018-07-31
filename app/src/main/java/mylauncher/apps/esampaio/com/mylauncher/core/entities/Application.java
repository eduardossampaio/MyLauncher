package mylauncher.apps.esampaio.com.mylauncher.core.entities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

public class Application {
    private ActivityInfo activityInfo;
    private Drawable icon;
    private String name;
    private Intent launchIntent;

    public Application(ActivityInfo activityInfo){
        this.activityInfo = activityInfo;
    }

    public void load(PackageManager packageManager){
        this.icon = this.activityInfo.loadIcon(packageManager);
        this.name = this.activityInfo.loadLabel(packageManager).toString();

        launchIntent = packageManager.getLaunchIntentForPackage(this.activityInfo.packageName);

    }
    public String getApplicationName(){
       return name;
    }

    public Drawable getApplicationIcon(){
        return this.icon;
    }

    public void launch(Context context){
        if (launchIntent != null) {
            context.startActivity(launchIntent);
        }
    }
}
