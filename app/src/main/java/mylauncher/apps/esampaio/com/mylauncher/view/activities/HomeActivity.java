package mylauncher.apps.esampaio.com.mylauncher.view.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

import mylauncher.apps.esampaio.com.mylauncher.R;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.Application;
import mylauncher.apps.esampaio.com.mylauncher.core.packages.PackageUtils;
import mylauncher.apps.esampaio.com.mylauncher.view.adapters.apps.AppsListPageAdapter;
import mylauncher.apps.esampaio.com.mylauncher.view.util.BitmapUtils;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager appsViewPager;
    private Bitmap[] backgroundsBitmaps;
    private ImageSwitcher imageSwitcher;
    private Toolbar toolbar;
    private ConstraintLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ArrayList<Application> applications = PackageUtils.getInstalledPackages(this);
        appsViewPager = findViewById(R.id.vp);
        imageSwitcher = findViewById(R.id.slide_trans_imageswitcher);
        rootLayout = findViewById(R.id.root_layout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(getString(R.string.my_apps));
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
        backgroundsBitmaps[0] = BitmapUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.wallpaper5, quality, quality);
        backgroundsBitmaps[1] = BitmapUtils.decodeSampledBitmapFromResource(getResources(), R.drawable.wallpaper6, quality, quality);
        setBackgroundImage(0);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        MenuItem item = menu.findItem(R.id.menu_item_settings);
        Drawable icon = item.getIcon();
        icon.setColorFilter(getResources().getColor(R.color.icons), PorterDuff.Mode.SRC_IN);

        item.setIcon(icon);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("HomeActivity","onResume");
        this.appsViewPager.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setBackgroundImage(position);
    }

    private void setBackgroundImage(final int position) {

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                try {
                    int realPosition = position % 2;
                    rootLayout.setBackground(new BitmapDrawable(getResources(), backgroundsBitmaps[realPosition]));
                } catch (Exception e) {

                }
            }
        }, 400);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
