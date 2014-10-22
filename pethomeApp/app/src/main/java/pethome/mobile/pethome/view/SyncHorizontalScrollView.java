package pethome.mobile.pethome.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class SyncHorizontalScrollView extends HorizontalScrollView {

    private View view;
    private int windowWitdh = 0;
    private Activity mContext;

    public void setSomeParam(View view, Activity context) {
        this.mContext = context;
        this.view = view;
        DisplayMetrics dm = new DisplayMetrics();
        this.mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        windowWitdh = dm.widthPixels;
    }

    public SyncHorizontalScrollView(Context context) {
        super(context);
    }

    public SyncHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//	public void showAndHideArrow() {
//		if (!mContext.isFinishing() && view != null) {
//			this.measure(0, 0);
//			if (windowWitdh >= this.getMeasuredWidth()) {
//				leftImage.setVisibility(View.GONE);
//				rightImage.setVisibility(View.GONE);
//			} else {
//				if (this.getLeft() == 0) {
//					leftImage.setVisibility(View.GONE);
//					rightImage.setVisibility(View.VISIBLE);
//				} else if (this.getRight() == 0) {
//					leftImage.setVisibility(View.VISIBLE);
//					rightImage.setVisibility(View.GONE);
//				} else {
//					leftImage.setVisibility(View.VISIBLE);
//					rightImage.setVisibility(View.VISIBLE);
//				}
//			}
//		}
//	}

    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
//        if (!mContext.isFinishing() && view != null && rightImage != null
//                && leftImage != null) {
//            if (view.getWidth() <= windowWitdh) {
//                leftImage.setVisibility(View.GONE);
//                rightImage.setVisibility(View.GONE);
//            } else {
//                if (l == 0) {
//                    leftImage.setVisibility(View.GONE);
//                    rightImage.setVisibility(View.VISIBLE);
//                } else if (view.getWidth() - l == windowWitdh) {
//                    leftImage.setVisibility(View.VISIBLE);
//                    rightImage.setVisibility(View.GONE);
//                } else {
//                    leftImage.setVisibility(View.VISIBLE);
//                    rightImage.setVisibility(View.VISIBLE);
//                }
//            }
//        }
    }
}
