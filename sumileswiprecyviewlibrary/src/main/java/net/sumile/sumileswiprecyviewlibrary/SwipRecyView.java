package net.sumile.sumileswiprecyviewlibrary;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;


/**
 * Created by sumile on 2016/8/2.
 */
public class SwipRecyView extends SwipeRefreshLayout {


    public SwipRecyView(Context context) {
        this(context, null);
    }

    private SumileRecyclerView sumileRecyclerView;
    private RecyclerView recyclerView;
    private SwipRecyView swipRecyView;
    private Context mContext;

    public SwipRecyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        swipRecyView = this;
        sumileRecyclerView = new SumileRecyclerView(context);
        recyclerView = sumileRecyclerView.getRefreshableView();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
//        this.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        addView(sumileRecyclerView);
        mContext = context;
    }

    public RecyclerView getRefreshableView() {
        return sumileRecyclerView.getRefreshableView();
    }

    public SumileRecyclerView getSumileRecyclerView() {
        return sumileRecyclerView;
    }

    private void init(RecyclerView.Adapter adapter, final OnSwipRecyListener callBack) {
//        recyclerView = initRecyclerView(mContext, recyclerView, adapter, callBack);
//        sumileRecyclerView = initSwipView();
//        initAction(callBack);
        recyclerView.setAdapter(adapter);
    }

    private void initAction(final OnSwipRecyListener callBack) {
        if (callBack != null) {
            OnRefreshListener listener = new OnRefreshListener() {
                @Override
                public void onRefresh() {
                    callBack.onPullDownToRefresh(sumileRecyclerView);
                }
            };
            setOnRefreshListener(listener);
            sumileRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
                @Override
                public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {

                }

                @Override
                public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                    callBack.onPullUpToRefresh(refreshView);
                }
            });
        }
    }

    public void setRefreshing(boolean show) {
        super.setRefreshing(show);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }


    private SumileRecyclerView initSwipView() {
        addView(sumileRecyclerView);
        return sumileRecyclerView;
    }

    //设置swipview的默认值
    private void setDefaultSwipRecyView() {
        setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
    }

    private void setDefaultSumileRecyclerView() {
        sumileRecyclerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        sumileRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        sumileRecyclerView.getLoadingLayoutProxy(false, true).setPullLabel("上拉加载");
    }

    private RecyclerView setDefaultRecyclerView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return recyclerView;
    }

    public void onRefreshComplete() {
        sumileRecyclerView.onRefreshComplete();
        setRefreshing(false);
    }


    public interface OnSwipRecyListener {

        void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView);

        void onPullDownToRefresh(SumileRecyclerView sumileRecyclerView);
    }

    public void setSwipRecyViewBuilder(SwipRecyViewBuilder builder) {
        if (builder.mSwipRecyView != null) {

        } else {
            setDefaultSwipRecyView();
        }
        if (builder.mCallBack != null) {
            initAction(builder.mCallBack);
        }
        if (builder.mSumileRecyclerView != null) {

        } else {
            setDefaultSumileRecyclerView();
        }
        if (builder.mRecyclerView != null) {

        } else {
            setDefaultRecyclerView();
        }
        recyclerView.setAdapter(builder.mAdapter);
    }

    public static class SwipRecyViewBuilder {
        public OnSwipRecyListener mCallBack;
        public SumileRecyclerView mSumileRecyclerView;
        public RecyclerView mRecyclerView;
        public SwipRecyView mSwipRecyView;
        public SwipRecyView mSwipRecyViewOld;
        public RecyclerView.Adapter mAdapter;

        public SwipRecyViewBuilder(SwipRecyView mSwipRecyView, RecyclerView.Adapter adapter) {
            this.mAdapter = adapter;
            this.mSwipRecyViewOld = mSwipRecyView;
        }

        public SwipRecyViewBuilder setOnSwipRecyRefreshListener(OnSwipRecyListener listener) {
            this.mCallBack = listener;
            return this;
        }

        public SwipRecyViewBuilder initPullToRefreshView(InitPullToRefreshView sumileRecyclerViewInterface) {
            this.mSumileRecyclerView = sumileRecyclerViewInterface.initSumileRecyclerView(mSwipRecyViewOld.getSumileRecyclerView());
            return this;
        }

        public SwipRecyViewBuilder initRecycleView(InitRecyclerView recyclerViewInterface) {
            this.mRecyclerView = recyclerViewInterface.initRecyclerView(mSwipRecyViewOld.getRefreshableView());
            return this;
        }

        public SwipRecyViewBuilder initSwipRecyView(InitSwipRecyView swipRecyViewInterface) {
            this.mSwipRecyView = swipRecyViewInterface.initSwipRecyView(mSwipRecyViewOld);
            return this;
        }

    }

    public interface InitPullToRefreshView {
        public SumileRecyclerView initSumileRecyclerView(SumileRecyclerView sumileRecyclerView);
    }

    public interface InitSwipRecyView {
        SwipRecyView initSwipRecyView(SwipRecyView swipRecyView);
    }

    public interface InitRecyclerView {
        RecyclerView initRecyclerView(RecyclerView recyclerView);
    }

}
