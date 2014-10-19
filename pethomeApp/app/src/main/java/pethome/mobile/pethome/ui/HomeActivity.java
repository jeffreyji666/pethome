package pethome.mobile.pethome.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import pethome.mobile.pethome.R;
import pethome.mobile.pethome.app.AppContext;

public class HomeActivity extends BaseActivity {
    private static final String TAG = "HomeActivity";

    private AppContext ctx;

    private RadioGroup footer;
    private RadioButton mFunnyMoment;
    private RadioButton mKnowledge;
    private RadioButton mNearby;
    private RadioButton mMine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

        ctx = (AppContext) getApplication();
        initView();
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
                    intent.setClass(HomeActivity.this, FunnyMomentActivity.class);
                    startActivity(intent);
                }
                if (rb.getText().equals(getResources().getString(R.string.nearby))) {
                    Intent intent = new Intent();
                    intent.setClass(HomeActivity.this, NearbyActivity.class);
                    startActivity(intent);
                }
                if (rb.getText().equals(getResources().getString(R.string.mine))) {
                    Intent intent = new Intent();
                    intent.setClass(HomeActivity.this, MineActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
