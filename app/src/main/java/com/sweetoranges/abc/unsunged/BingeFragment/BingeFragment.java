package com.sweetoranges.abc.unsunged.BingeFragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sweetoranges.abc.unsunged.Adapters.UpdatableFragmentPagerAdapter;
import com.sweetoranges.abc.unsunged.Classes.StreamingRequest;
import com.sweetoranges.abc.unsunged.MainActivity;
import com.sweetoranges.abc.unsunged.Model.Binge;
import com.sweetoranges.abc.unsunged.Model.Story;
import com.sweetoranges.abc.unsunged.R;
import com.sweetoranges.abc.unsunged.Story.StoryAdapter;
import com.sweetoranges.abc.unsunged.TrendingFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import retrofit2.Call;
import retrofit2.Response;

public class BingeFragment extends Fragment  {
    private RecyclerView storyRecycler;
    private RecyclerView sharedRecycler;
    private List<StreamingRequest> follow = new ArrayList<>();
    private List<Story> storyList=new ArrayList<Story>();
    private List<Binge> sharedList=new ArrayList<Binge>();
    ProgressBar progressBar;
    AppCompatImageView firstImage;

    private ViewPager pager = null;
    private ArrayList<Integer> sDataSet;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_binge, container, false);
        if (savedInstanceState == null) {
            sDataSet = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                sDataSet.add(i);
            }
        } else {
            sDataSet = savedInstanceState.getIntegerArrayList("dataset");
        }
//        try{ ObjectAnimator colorFade = ObjectAnimator.ofObject(view, "backgroundColor", new ArgbEvaluator(), Color.argb(255,255,255,255), 0xff000000);
//            colorFade.setDuration(7000);
//            colorFade.start();
//        }catch (Exception c){}
        firstImage=(AppCompatImageView)view.findViewById(R.id.firsthero1);
        AppCompatTextView first = view.findViewById(R.id.firsttext);
        AppCompatTextView second = view.findViewById(R.id.secondtext);
        progressBar=(ProgressBar)view.findViewById(R.id.progressBar);
        AppCompatTextView third = view.findViewById(R.id.thirdtext);
        AppCompatTextView forth = view.findViewById(R.id.forthtext);
        AppCompatTextView fifth = view.findViewById(R.id.fifthtext);
        pager=(ViewPager)view.findViewById(R.id.view_pager) ;

        final PagerAdapter adapter = new PagerAdapter(getActivity().getSupportFragmentManager(), sDataSet);
        pager.setAdapter(adapter);


        first.setSelected(true);
        second.setSelected(true);
        third.setSelected(true);
        forth.setSelected(true);
        fifth.setSelected(true);
     //   followings=getFollowingsId();
        storyRecycler = view.findViewById(R.id.story);
        sharedRecycler=view.findViewById(R.id.shared);

        storyRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        storyRecycler.setItemAnimator(new DefaultItemAnimator());
        storyRecycler.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                storyRecycler, new ClickListener() {
            @Override public void onClick(View view, final int position) {
                RelativeLayout rl=(RelativeLayout) view.findViewById(R.id.storyspace);
                rl.setOnClickListener(v -> scroll());
            }

            @Override public void onLongClick(View view, int position) { }
        }));


        sharedRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        sharedRecycler.setItemAnimator(new DefaultItemAnimator());

        if(isNetworkAvailable()){
           progressBar.setVisibility(View.VISIBLE);
            loadStory();
            loadShared();
            new Handler().postDelayed(() -> progressBar.setVisibility(View.GONE), 1200);
        }


        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        return view;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private void loadStory() {
         Call<StreamingRequest> call = MainActivity.apiService.getStory();
            call.enqueue(new retrofit2.Callback<StreamingRequest>() {
                @Override
                public void onResponse(Call<StreamingRequest> call, Response<StreamingRequest> response) {
                    follow.add(response.body());
                    storyRecycler.setAdapter(new StoryAdapter(getActivity(),storyList));
                }
                @Override
                public void onFailure(Call<StreamingRequest> call, Throwable t) { }
            });
    }

    private void loadShared(){
        Call<List<Binge>> call = MainActivity.apiService.getShared();
        call.enqueue(new retrofit2.Callback<List<Binge>>() {
            @Override
            public void onResponse(Call<List<Binge>> call, Response<List<Binge>> response) {
                sharedList=response.body();
               // storyRecycler.setAdapter(new BingeAdapter(getActivity(),sharedList));
            }
            @Override
            public void onFailure(Call<List<Binge>> call, Throwable t) { }
        });
    }

    public void scroll(){
        storyRecycler.smoothScrollBy(700, 0);
    }

    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    private static class PagerAdapter extends UpdatableFragmentPagerAdapter {

        private final List<Integer> mItems;

        PagerAdapter(FragmentManager fm, List<Integer> items) {
            super(fm);
            mItems = items;
        }

        @Override
        public Fragment getItem(int position) {
            return TrendingFragment.newInstance(mItems.get(position));
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public long getItemId(int position) {
            return mItems.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            TrendingFragment item = (TrendingFragment) object;
            int itemValue = item.getSomeIdentifier();
            for (int i = 0; i < mItems.size(); i++) {
                if (mItems.get(i).equals(itemValue)) {
                    return i;
                }
            }
            return POSITION_NONE;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList("dataset", sDataSet);
    }
}