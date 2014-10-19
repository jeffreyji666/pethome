package pethome.mobile.pethome.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import pethome.mobile.pethome.R;

/**
 * Created by you on 10/19/14.
 */
public class FunnyMomentActivity extends FragmentActivity {
    private static final String TAG = "FunnyMomentActivity";

    private ViewPager mViewPager;
    private FragmentStatePagerAdapter mAdapter;
    private List<Fragment> mDatas;

    private RadioGroup header;
    private RadioButton mDog;
    private RadioButton mCat;
    private RadioButton mLittle;
    private RadioButton mSea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.funny_moment);

        initView();
    }


    private void initView(){
        header = (RadioGroup) findViewById(R.id.header);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mDog = (RadioButton) findViewById(R.id.mFunnyMoment);
        mLittle = (RadioButton) findViewById(R.id.mNearby);
        mCat = (RadioButton) findViewById(R.id.mKnowledge);
        mSea = (RadioButton) findViewById(R.id.mMine);

        mDatas = new ArrayList<Fragment>();
        DogFragment dog = new DogFragment();
        CatFragment cat = new CatFragment();
        LittleFragment little = new LittleFragment();
        SeaFragment sea = new SeaFragment();
        mDatas.add(dog);
        mDatas.add(cat);
        mDatas.add(little);
        mDatas.add(sea);

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
            public void onPageScrolled(int position, float offset, int offsetPixel) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        mDog.setChecked(true);
                        break;
                    case 1:
                        mLittle.setChecked(true);
                        break;
                    case 2:
                        mCat.setChecked(true);
                        break;
                    case 3:
                        mSea.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //绑定一个匿名监听器
        header.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                //获取变更后的选中项的ID
                int radioButtonId = arg0.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                RadioButton rb = (RadioButton) findViewById(radioButtonId);
                //更新文本内容，以符合选中项
                int currentIndex = 0;
                if (rb.getText().equals(getResources().getString(R.string.knowledge))) {
                    currentIndex = 1;
                }
                if (rb.getText().equals(getResources().getString(R.string.nearby))) {
                    currentIndex = 2;
                }
                if (rb.getText().equals(getResources().getString(R.string.mine))) {
                    currentIndex = 3;
                }
                mViewPager.setCurrentItem(currentIndex);
            }
        });
    }
}
