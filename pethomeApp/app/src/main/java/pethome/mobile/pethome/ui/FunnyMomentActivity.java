package pethome.mobile.pethome.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pethome.mobile.pethome.R;
import pethome.mobile.pethome.app.AppContext;

/**
 * Created by you on 10/19/14.
 */
public class FunnyMomentActivity extends BaseFragmentActivity {
    private static final String TAG = "FunnyMomentActivity";

    private AppContext ctx;
    private FragmentStatePagerAdapter mAdapter;
    private List<Fragment> mDatas;

    private ViewPager mViewPager;
    private TextView tDog;
    private TextView tCat;
    private TextView tOther;
    private LinearLayout lDog;

    private ImageView mTabline;
    private int mScreenDivBy3;
    private int mCurrentPageIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.funny_moment);
        ctx = (AppContext) getApplication();

        initTabLine();
        initView();
    }


    private void initTabLine() {
        mTabline = (ImageView) findViewById(R.id.tabline);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreenDivBy3 = outMetrics.widthPixels / 3;
        ViewGroup.LayoutParams lp = mTabline.getLayoutParams();
        lp.width = mScreenDivBy3;
        mTabline.setLayoutParams(lp);
    }

    private void initView() {
        lDog = (LinearLayout) findViewById(R.id.lDog);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        tDog = (TextView) findViewById(R.id.tDog);
        tCat = (TextView) findViewById(R.id.tCat);
        tOther = (TextView) findViewById(R.id.tOther);

        mDatas = new ArrayList<Fragment>();
        DogFragment dog = new DogFragment();
        CatFragment cat = new CatFragment();
        OtherFragment other = new OtherFragment();
        mDatas.add(dog);
        mDatas.add(cat);
        mDatas.add(other);

        mAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mDatas.get(i);
            }

            @Override
            public int getCount() {
                return mDatas.size();
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPx) {
                LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) mTabline
                        .getLayoutParams();
                lp.leftMargin = (int) (mScreenDivBy3 * positionOffset + position * mScreenDivBy3);
                mTabline.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tDog.setTextColor(Color.parseColor("#2f75bb"));
                        break;
                    case 1:
                        tCat.setTextColor(Color.parseColor("#2f75bb"));
                        break;
                    case 2:
                        tOther.setTextColor(Color.parseColor("#2f75bb"));
                        break;
                }
                mCurrentPageIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
}
