package mylauncher.apps.esampaio.com.mylauncher.core.entities;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

import mylauncher.apps.esampaio.com.mylauncher.R;

public class Application implements Serializable {

//    private Drawable icon;
    private String name;
    private String packageName;

    public void load(ResolveInfo resolveInfo,PackageManager packageManager){
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        this.name = activityInfo.loadLabel(packageManager).toString();
        this.packageName = activityInfo.packageName;

    }

    public String getApplicationName(){
       return name;
    }

    public Drawable getApplicationIcon(Context context){
        PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getApplicationIcon(this.packageName);
        } catch (PackageManager.NameNotFoundException e) {
            return context.getDrawable(R.mipmap.ic_launcher);
        }

    }

    public void launch(Context context){
        PackageManager packageManager = context.getPackageManager();
        Intent launchIntent =  packageManager.getLaunchIntentForPackage(this.packageName);
        if (launchIntent != null) {
            context.startActivity(launchIntent);
        }
    }
}
