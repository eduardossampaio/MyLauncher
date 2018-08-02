package mylauncher.apps.esampaio.com.mylauncher.view.wallpaper;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import mylauncher.apps.esampaio.com.mylauncher.R;

//https://stackoverflow.com/questions/20053919/programmatically-set-android-phones-background
public class WallpaperService {
    public static final WallpaperService instance = new WallpaperService();

    private WallpaperService(){

    }
    public void changeSystemWallpaper(Context context,int wallpaperResourceId){
        changeSystemWallpaper(context,context.getResources().getDrawable(wallpaperResourceId,null));
    }
    public void changeSystemWallpaper(Context context,Drawable wallpaperDrawable){
        WallpaperManager myWallpaperManager = WallpaperManager.getInstance(context);
        try {
            Bitmap bitmap = drawableToBitmap(wallpaperDrawable);
            myWallpaperManager.setBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewWallpaper(){

    }

    public void setWallpaper(Context context, ImageView destinationImageView, int page){
        try{
            int wallpaperResourceId = page;//getWallpaperForPage(page);
            changeSystemWallpaper(context,wallpaperResourceId);
            Picasso.get().load(wallpaperResourceId).fit().into(destinationImageView);
        }catch (Exception e){

        }
    }



    public static Bitmap drawableToBitmap (Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if(bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if(drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }


}
