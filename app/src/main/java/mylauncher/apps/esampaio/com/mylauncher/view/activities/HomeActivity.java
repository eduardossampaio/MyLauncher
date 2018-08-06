package mylauncher.apps.esampaio.com.mylauncher.view.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

import mylauncher.apps.esampaio.com.mylauncher.R;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.InstalledApplication;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.Launchable;
import mylauncher.apps.esampaio.com.mylauncher.core.packages.PackageUtils;
import mylauncher.apps.esampaio.com.mylauncher.view.adapters.apps.AppsListPageAdapter;
import mylauncher.apps.esampaio.com.mylauncher.view.customizers.background.BackgroundCustomizer;
import mylauncher.apps.esampaio.com.mylauncher.view.customizers.background.MultipleBackgroundCustomizer;
import mylauncher.apps.esampaio.com.mylauncher.view.util.PixelUtils;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager appsViewPager;
    private ViewGroup rootLayout;
    private BackgroundCustomizer backgroundCustomizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ArrayList<Launchable> applications = PackageUtils.getLaunchables(this);
        appsViewPager = findViewById(R.id.vp);
        rootLayout = findViewById(R.id.root_layout);
        appsViewPager.addOnPageChangeListener(this);
        appsViewPager.setPadding(0,0,0, PixelUtils.getNavigationBarHeight(this));
        AppsListPageAdapter pageAdapter = new AppsListPageAdapter(getSupportFragmentManager(), this, applications);
        appsViewPager.setAdapter(pageAdapter);
        this.backgroundCustomizer = new MultipleBackgroundCustomizer(this);
        backgroundCustomizer.apply(rootLayout,appsViewPager.getCurrentItem());
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
                    backgroundCustomizer.apply(rootLayout,realPosition);
                } catch (Exception e) {

                }
            }
        }, 400);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
