package mylauncher.apps.esampaio.com.mylauncher.core.entities;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public interface Launchable {

    String getApplicationName();

    Drawable getApplicationIcon(Context context);

    void launch(Context context, Bundle parameters);

}
