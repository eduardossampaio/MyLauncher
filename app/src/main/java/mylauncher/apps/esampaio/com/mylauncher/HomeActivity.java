package mylauncher.apps.esampaio.com.mylauncher;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.huanhailiuxin.coolviewpager.CoolViewPager;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        CoolViewPager vp = findViewById(R.id.vp);
        vp.setScrollMode(CoolViewPager.ScrollMode.HORIZONTAL);
        vp.setAdapter(new AppsListPageAdapter(this));
        vp.setAutoScrollDirection(CoolViewPager.AutoScrollDirection.BACKWARD);
        vp.setInfiniteLoop(true);
        vp.setEdgeEffectColor(getResources().getColor(R.color.colorPrimary));


//        ViewPager vp = findViewById(R.id.vp);
//        vp.setAdapter(new AppsListPageAdapter(this));
    }
}
