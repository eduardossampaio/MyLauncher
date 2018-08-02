package mylauncher.apps.esampaio.com.mylauncher.view.activities;

import android.graphics.drawable.Drawable;
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

import java.util.ArrayList;
import java.util.List;

import mylauncher.apps.esampaio.com.mylauncher.core.entities.Application;
import mylauncher.apps.esampaio.com.mylauncher.core.packages.PackageUtils;
import mylauncher.apps.esampaio.com.mylauncher.view.adapters.apps.AppsListPageAdapter;
import mylauncher.apps.esampaio.com.mylauncher.R;
import mylauncher.apps.esampaio.com.mylauncher.view.wallpaper.WallpaperService;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager appsViewPager;
    private Drawable[] backgrounds;
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
        appsViewPager.setAdapter(new AppsListPageAdapter(getSupportFragmentManager(),this,applications));
        //temporario
        backgrounds = new Drawable[2];
        backgrounds[0] = getResources().getDrawable(R.drawable.wallpaper5,null);
        backgrounds[1] = getResources().getDrawable(R.drawable.wallpaper6,null);

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
        Log.d("HomeActivity",position+"");
        setBackgroundImage(position);
    }

    private void setBackgroundImage(int position){
        imageSwitcher.setImageDrawable(backgrounds[position]);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
