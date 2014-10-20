package pethome.mobile.pethome.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.umeng.analytics.MobclickAgent;

import pethome.mobile.pethome.R;

/**
 * Created by you on 10/19/14.
 */
public class BaseFragmentActivity extends FragmentActivity {
    private static final String TAG = "BaseFragmentActivity";

    private RadioGroup footer;
    private RadioButton mFunnyMoment;
    private RadioButton mKnowledge;
    private RadioButton mNearby;
    private RadioButton mMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.footer);

        initView();
        //捕捉未捕捉异常，通过调用这个方法，可以收集引起程序异常退出的错误信息，如不用可注释
        MobclickAgent.onEvent(this,this.toString());
    }


    private void initView() {
        footer = (RadioGroup) findViewById(R.id.footer);
        mFunnyMoment = (RadioButton) findViewById(R.id.mFunnyMoment);
        mNearby = (RadioButton) findViewById(R.id.mNearby);
        mKnowledge = (RadioButton) findViewById(R.id.mKnowledge);
        mMine = (RadioButton) findViewById(R.id.mMine);

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
                    intent.setClass(BaseFragmentActivity.this, FunnyMomentActivity.class);
                    startActivity(intent);
                }
                if (rb.getText().equals(getResources().getString(R.string.nearby))) {
                    Intent intent = new Intent();
                    intent.setClass(BaseFragmentActivity.this, NearbyActivity.class);
                    startActivity(intent);
                }
                if (rb.getText().equals(getResources().getString(R.string.mine))) {
                    Intent intent = new Intent();
                    intent.setClass(BaseFragmentActivity.this, MineActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * 在重载函数中为统计代码 *
     */
    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
}
