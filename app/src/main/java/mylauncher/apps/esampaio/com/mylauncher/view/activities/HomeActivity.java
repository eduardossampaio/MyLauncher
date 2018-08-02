package mylauncher.apps.esampaio.com.mylauncher.view.activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import com.huanhailiuxin.coolviewpager.CoolViewPager;
import com.huanhailiuxin.coolviewpager.adapter.LoopPagerAdapterWrapper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.zelory.compressor.Compressor;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.Application;
import mylauncher.apps.esampaio.com.mylauncher.core.packages.PackageUtils;
import mylauncher.apps.esampaio.com.mylauncher.view.adapters.apps.AppsListPageAdapter;
import mylauncher.apps.esampaio.com.mylauncher.R;
import mylauncher.apps.esampaio.com.mylauncher.view.custom.InfinitePagerAdapter;
import mylauncher.apps.esampaio.com.mylauncher.view.util.BitmapUtils;
import mylauncher.apps.esampaio.com.mylauncher.view.wallpaper.WallpaperService;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager appsViewPager;
    private Bitmap[] backgroundsBitmaps;
    private ImageSwitcher imageSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ArrayList<Application> applications = PackageUtils.getInstalledPackages(this);
        appsViewPager = findViewById(R.id.vp);
        imageSwitcher = findViewById(R.id.slide_trans_imageswitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                myView.setLayoutParams(new
                        ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));
                return myView;
            }
        });
        appsViewPager.addOnPageChangeListener(this);

        AppsListPageAdapter pageAdapter = new AppsListPageAdapter(getSupportFragmentManager(), this, applications);
        appsViewPager.setAdapter(pageAdapter);
        //temporario
        backgroundsBitmaps = new Bitmap[2];
        int quality = 600;
        backgroundsBitmaps[0] = BitmapUtils. decodeSampledBitmapFromResource(getResources(), R.drawable.wallpaper5, quality, quality);
        backgroundsBitmaps[1] = BitmapUtils. decodeSampledBitmapFromResource(getResources(), R.drawable.wallpaper6, quality, quality);
        setBackgroundImage(0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.appsViewPager.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setBackgroundImage(position);
    }

    private void setBackgroundImage(final int position){

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
            int realPosition = position % 2;
            imageSwitcher.setImageDrawable(new BitmapDrawable(getResources(),backgroundsBitmaps[realPosition]));
        }catch (Exception e){

        }
            }
        },100);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
