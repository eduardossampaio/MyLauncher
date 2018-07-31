package mylauncher.apps.esampaio.com.mylauncher.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huanhailiuxin.coolviewpager.CoolViewPager;

import java.util.List;

import mylauncher.apps.esampaio.com.mylauncher.core.entities.Application;
import mylauncher.apps.esampaio.com.mylauncher.core.packages.PackageUtils;
import mylauncher.apps.esampaio.com.mylauncher.view.adapters.apps.AppsListPageAdapter;
import mylauncher.apps.esampaio.com.mylauncher.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CoolViewPager vp = findViewById(R.id.vp);
        vp.setScrollMode(CoolViewPager.ScrollMode.HORIZONTAL);
        List<Application> applications = PackageUtils.getInstalledPackages(this);
        vp.setAdapter(new AppsListPageAdapter(this,applications));
        vp.setAutoScrollDirection(CoolViewPager.AutoScrollDirection.BACKWARD);
        vp.setInfiniteLoop(true);
//        vp.setPageTransformer(false,new FadeOutTransformation());
        vp.setEdgeEffectColor(getResources().getColor(R.color.colorPrimary));

//
//        ViewPager vp = findViewById(R.id.vp);
//        vp.setAdapter(new AppsListPageAdapter(this));
    }
}
