package mylauncher.apps.esampaio.com.mylauncher;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

public class AppsListPageAdapter extends PagerAdapter {
    private Context context;

    public AppsListPageAdapter(Context context){
        this.context = context;
    }
    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return  view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View baseLayout = LayoutInflater.from(this.context).inflate(R.layout.apps_page,container,false);
        ImageView backgroundImage = baseLayout.findViewById(R.id.background);
        RecyclerView appsList = baseLayout.findViewById(R.id.apps_list);
        int yourColumn = 4;
        appsList.addItemDecoration( new LayoutMarginDecoration( yourColumn, 30 ) );
        appsList.setAdapter(new AppsListAdapter(this.context));
        appsList.setLayoutManager(new GridLayoutManager(this.context,4));
        if(position % 3 ==0){
            backgroundImage.setImageResource(R.drawable.wallpaper1);
        }else if(position % 2 == 0){
            backgroundImage.setImageResource(R.drawable.wallpaper2);
        }else{
            backgroundImage.setImageResource(R.drawable.wallpaper3);
        }
        container.addView(baseLayout);
        return baseLayout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }
}
