package mylauncher.apps.esampaio.com.mylauncher.core.entities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import java.io.Serializable;

public class LaunchableActivity implements Launchable,Serializable {
    private String displayName;
    private int resourceDrawable;
    private String activityClassName;

    public LaunchableActivity(String displayName, int resourceDrawable, Class activityClass) {
        this.displayName = displayName;
        this.resourceDrawable = resourceDrawable;
        this.activityClassName = activityClass.getName();
    }

    @Override
    public String getApplicationName() {
        return displayName;
    }

    @Override
    public Drawable getApplicationIcon(Context context) {
        return context.getDrawable(resourceDrawable);
    }

    @Override
    public void launch(Context context, Bundle parameters) {
        try {
            Intent intent = new Intent(context,Class.forName(activityClassName));
            if( parameters != null){
                intent.putExtras(parameters);
            }
            context.startActivity(intent);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
