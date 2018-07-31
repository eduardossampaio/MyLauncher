package mylauncher.apps.esampaio.com.mylauncher.view.adapters.apps;

import android.content.Context;
import android.support.annotation.NonNull;

import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.thekhaeng.recyclerviewmargin.LayoutMarginDecoration;

import java.util.List;

import mylauncher.apps.esampaio.com.mylauncher.R;
import mylauncher.apps.esampaio.com.mylauncher.core.entities.Application;
import mylauncher.apps.esampaio.com.mylauncher.view.adapters.apps.AppsListAdapter;

public class AppsListPageAdapter extends PagerAdapter {
    private Context context;
    private List<Application> applicationList;
    private int pagesCount = 0;

    public AppsListPageAdapter(Context context,List<Application> applicationList){
        this.context = context;
        this.applicationList = applicationList;
        if(applicationList.size() < 24){
            this.pagesCount = 1;
        }else {
            this.pagesCount = applicationList.size() / 24;
            if( applicationList.size() % 24 != 0){
                this.pagesCount++;
            }
        }
    }
    @Override
    public int getCount() {
        return pagesCount;
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
//        int yourColumn = 4;
//        appsList.addItemDecoration( new LayoutMarginDecoration( yourColumn, 30 ) );
        appsList.setAdapter(new AppsListAdapter(this.context,sublist(position)));
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

    private List<Application> sublist(int pageNumber){
        int min = 24 * pageNumber;
        int max = min + 24;
        if(max >= applicationList.size()){
            max = applicationList.size();
        }
        return applicationList.subList(min,max);
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }
}
