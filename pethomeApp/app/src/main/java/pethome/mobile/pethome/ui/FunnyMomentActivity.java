package pethome.mobile.pethome.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import pethome.mobile.pethome.R;
import pethome.mobile.pethome.app.AppContext;
import pethome.mobile.pethome.view.SyncHorizontalScrollView;

/**
 * Created by you on 10/19/14.
 */
public class FunnyMomentActivity extends BaseFragmentActivity {
    private static final String TAG = "FunnyMomentActivity";

    private AppContext ctx;

    private RelativeLayout rlNav;
    private SyncHorizontalScrollView scrollView;
    private RadioGroup rgNavContent;
    private ImageView ivNavIndicator;
    private ImageView ivNavLeft;
    private ImageView ivNavRight;
    private ViewPager mViewPager;
    private RadioGroup footer;
    private LayoutInflater mInflater;
    private TabFragmentPagerAdapter mAdapter;

    private int currentIndicatorLeft = 0;
    public static String[] tabTitle = {"汪星人", "喵星人", "其它"};
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
        ViewGroup.LayoutParams cursor_Params = ivNavIndicator.getLayoutParams();
        cursor_Params.width = indicatorWidth;
        ivNavIndicator.setLayoutParams(cursor_Params);
        scrollView.setSomeParam(rlNav, ivNavLeft, ivNavRight, this);

        mInflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        initNavigationHSV();

        mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager());
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
        scrollView = (SyncHorizontalScrollView) findViewById(R.id.mHsv);
        rgNavContent = (RadioGroup) findViewById(R.id.rg_nav_content);
        ivNavIndicator = (ImageView) findViewById(R.id.iv_nav_indicator);
        ivNavLeft = (ImageView) findViewById(R.id.iv_nav_left);
        ivNavRight = (ImageView) findViewById(R.id.iv_nav_right);

        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        footer = (RadioGroup) findViewById(R.id.footer);

    }

    public static class TabFragmentPagerAdapter extends FragmentStatePagerAdapter {
        public TabFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            Fragment ft = null;
            switch (i) {
                case 0:
                    ft = new DogFragment();
                    break;
                case 1:
                    ft = new CatFragment();
                    break;
                case 2:
                    ft = new OtherFragment();
                    break;
                default:
                    ft = new DogFragment();
                    break;
            }
            return ft;
        }

        @Override
        public int getCount() {
            return tabTitle.length;
        }
    }
}
