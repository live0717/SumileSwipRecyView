# SumileSwipRecyView
使用方法

  layout中
  
       <net.sumile.sumileswiprecyview.SwipRecyView
            android:layout_width="match_parent"
            android:layout_height="match_parent">
        </net.sumile.sumileswiprecyview.SwipRecyView>
        
  
  代码中进行设置
    
    SwipRecyView.SwipRecyViewBuilder builder = new SwipRecyViewBuilder(srView, adapter);
        builder.setOnSwipRecyRefreshListener(new OnSwipRecyListener() {
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                getDataWithPage(++page);
            }

            @Override
            public void onPullDownToRefresh(SumileRecyclerView sumileRecyclerView) {
                getDataWithPage(1);
            }
        }).initSwipRecyView(new InitSwipRecyView() {
            @Override
            public SwipRecyView initSwipRecyView(SwipRecyView swipRecyView) {
                swipRecyView.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_green_light);
                return swipRecyView;
            }
        });
        srView.setSwipRecyViewBuilder(builder);
  
  Builder中共有四个方法，一个设置上拉下拉的回调，另外三个分别是设置
        SwipeRefreshLayout：InitSwipRecyView、
        PullToRefresh:InitPullToRefreshView、
        RecyclerView:InitRecyclerView的，
        在获得到这三个控件之后，可以对它设置，然后将它返回就行，如果返回为空，则使用默认的设置，比较简陋。（其实返回的时候只要不是空就行）

博客中的相应文章：http://sumile.cn/archives/1588.html
  
