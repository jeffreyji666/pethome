package pethome.mobile.pethome.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

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

    private RelativeLayout rlNav;
    private HorizontalScrollView scrollView;
    private RadioGroup rgNavContent;
    private ImageView ivNavIndicator;
    private ViewPager mViewPager;
    private RadioGroup footer;
    private LayoutInflater mInflater;
    private FragmentStatePagerAdapter mAdapter;
    private List<Fragment> mDatas;

    private int currentIndicatorLeft = 0;
    public String[] tabTitle = {"汪星人", "喵星人", "其它"};
    private int indicatorWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.funny_moment);

        findViewById();
        initView();
        setListener();
    }

    private void setListener() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (rgNavContent != null && rgNavContent.getChildCount() > position) {
                    ((RadioButton) rgNavContent.getChildAt(position)).performClick();
                }
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        rgNavContent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rgNavContent.getChildAt(checkedId) != null) {
                    TranslateAnimation animation = new TranslateAnimation(
                            currentIndicatorLeft,
                            ((RadioButton) rgNavContent.getChildAt(checkedId)).getLeft(), 0f, 0f);
                    animation.setInterpolator(new LinearInterpolator());
                    animation.setDuration(100);
                    animation.setFillAfter(true);

                    ivNavIndicator.startAnimation(animation);
                    mViewPager.setCurrentItem(checkedId);
                    currentIndicatorLeft = ((RadioButton) rgNavContent.getChildAt(checkedId)).getLeft();
                    scrollView.smoothScrollTo(
                            (checkedId > 1 ? ((RadioButton) rgNavContent.getChildAt(checkedId)).getLeft() : 0) - ((RadioButton) rgNavContent.getChildAt(2)).getLeft(), 0);
                }
            }
        });

        //绑定一个匿名监听器
        footer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                //获取变更后的选中项的ID
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton) findViewById(radioButtonId);
                //更新文本内容，以符合选中项
                if (rb.getText().equals(getResources().getString(R.string.knowledge))) {
                    Intent intent = new Intent();
                    intent.setClass(FunnyMomentActivity.this, FunnyMomentActivity.class);
                    startActivity(intent);
                }
                if (rb.getText().equals(getResources().getString(R.string.nearby))) {
                    Intent intent = new Intent();
                    intent.setClass(FunnyMomentActivity.this, NearbyActivity.class);
                    startActivity(intent);
                }
                if (rb.getText().equals(getResources().getString(R.string.mine))) {
                    Intent intent = new Intent();
                    intent.setClass(FunnyMomentActivity.this, MineActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void initView() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        indicatorWidth = dm.widthPixels / tabTitle.length;
        ViewGroup.LayoutParams cursorParams = ivNavIndicator.getLayoutParams();
        cursorParams.width = indicatorWidth;
        ivNavIndicator.setLayoutParams(cursorParams);

        mInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        initNavigationHSV();

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
    }

    private void initNavigationHSV() {
        rgNavContent.removeAllViews();
        for (int i = 0; i < tabTitle.length; i++) {
            RadioButton rb = (RadioButton) mInflater.inflate(R.layout.nav_radiogroup_item, null);
            rb.setId(i);
            rb.setText(tabTitle[i]);
            rb.setLayoutParams(new ViewGroup.LayoutParams(indicatorWidth,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            rgNavContent.addView(rb);
        }
    }

    private void findViewById() {
        rlNav = (RelativeLayout) findViewById(R.id.rl_nav);
        scrollView = (HorizontalScrollView) findViewById(R.id.scrollView);
        rgNavContent = (RadioGroup) findViewById(R.id.rg_nav_content);
        ivNavIndicator = (ImageView) findViewById(R.id.iv_nav_indicator);

        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        footer = (RadioGroup) findViewById(R.id.footer);
    }
}
