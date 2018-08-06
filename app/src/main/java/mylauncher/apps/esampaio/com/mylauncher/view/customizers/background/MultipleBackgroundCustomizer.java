package mylauncher.apps.esampaio.com.mylauncher.view.customizers.background;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;

import mylauncher.apps.esampaio.com.mylauncher.R;
import mylauncher.apps.esampaio.com.mylauncher.view.customizers.Customizer;
import mylauncher.apps.esampaio.com.mylauncher.view.util.BitmapUtils;
import mylauncher.apps.esampaio.com.mylauncher.view.util.PixelUtils;

public class MultipleBackgroundCustomizer implements BackgroundCustomizer{

    private Bitmap[] backgroundsBitmaps;
    private Drawable[] backgroundDrawables;

    private Context context;

    public MultipleBackgroundCustomizer(Context context){
        this.context = context;
        //temporario
        backgroundsBitmaps = new Bitmap[2];
        int quality = 600;
        backgroundsBitmaps[0] = BitmapUtils.decodeSampledBitmapFromResource(context.getResources(), R.drawable.wallpaper5, quality, quality);
        backgroundsBitmaps[1] = BitmapUtils.decodeSampledBitmapFromResource(context.getResources(), R.drawable.wallpaper6, quality, quality);
        int actionBarSize = PixelUtils.getActionBarHeight(context) + 100+ (int) PixelUtils.dpToPixel(context,24);
        backgroundsBitmaps[0] = BitmapUtils.blurTopImage(backgroundsBitmaps[0],actionBarSize);

        backgroundsBitmaps[1] = BitmapUtils.blurTopImage(backgroundsBitmaps[1],actionBarSize);
        backgroundDrawables = new Drawable[2];
        backgroundDrawables[0]= new BitmapDrawable(context.getResources(), backgroundsBitmaps[0]);
        backgroundDrawables[1]= new BitmapDrawable(context.getResources(), backgroundsBitmaps[1]);

    }
    @Override
    public void apply(View view,int forPage){
        if ( view instanceof ImageSwitcher){
            ((ImageSwitcher) view).setImageDrawable(backgroundDrawables[forPage]);
        }else if (view instanceof ImageView){
            ((ImageView) view).setImageDrawable(backgroundDrawables[forPage]);
        }else{
            view.setBackground(backgroundDrawables[forPage]);
        }
    }
}
