package net.sumile.sumileswiprecyviewlibrary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by Administrator on 2016/8/5.
 */
public class SumileRecyclerView extends PullToRefreshBase<RecyclerView> {
    public SumileRecyclerView(Context context) {
        super(context);
    }

    public SumileRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SumileRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public SumileRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);

    }

    //重写4个方法
    //1 滑动方向
    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    //重写4个方法
    //2  滑动的View
    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView view = new RecyclerView(context, attrs);
        return view;
    }

    //重写4个方法
    //3 是否滑动到底部
    @Override
    protected boolean isReadyForPullEnd() {
//        View view = getRefreshableView().getChildAt(getRefreshableView().getChildCount() - 1);
        View view = getRefreshableView().getLayoutManager().getChildAt(getRefreshableView().getChildCount() - 1);

        if (null != view) {
            RecyclerView.Adapter adapter = getRefreshableView().getAdapter();
            if (adapter!=null){

                if (adapter.getItemCount()-1!=getRefreshableView().getLayoutManager().getPosition(view)){
                    return false;
                }
            }
            return getRefreshableView().getBottom() >= view.getBottom();
        }
        return false;
    }

    //重写4个方法
    //4 是否滑动到顶部
    @Override
    protected boolean isReadyForPullStart() {
//        View view = getRefreshableView().getChildAt(0);
//
//        if (view != null) {
//            return view.getTop() >= getRefreshableView().getTop();
//        }
        return false;
    }

}
