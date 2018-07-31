package mylauncher.apps.esampaio.com.mylauncher.view.animations;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Source: https://github.com/dipanshukr/Viewpager-Transformation?utm_source=android-arsenal.com&utm_medium=referral&utm_campaign=6850
 */
public class FadeOutTransformation implements ViewPager.PageTransformer{
    @Override
    public void transformPage(View page, float position) {

        page.setTranslationX(-position*page.getWidth());

        page.setAlpha(1-Math.abs(position));

    }
}