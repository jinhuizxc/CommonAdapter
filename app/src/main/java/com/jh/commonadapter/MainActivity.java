package com.jh.commonadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.jinhui.irecyclerview.IRecyclerView;
import com.jinhui.irecyclerview.OnLoadMoreListener;
import com.jinhui.irecyclerview.OnRefreshListener;
import com.jinhui.irecyclerview.utils.LogUtils;
import com.jinhui.irecyclerview.widget.LoadMoreFooterView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnRefreshListener, OnLoadMoreListener {


    @BindView(R.id.irc_myaidou)
    IRecyclerView iRecyclerView;

    private MyAdapter myAdapter;
    private List<String> mData = new ArrayList<>();

    int currentPage = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        iRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this, mData);
        iRecyclerView.setAdapter(myAdapter);
        iRecyclerView.setOnRefreshListener(this);
        iRecyclerView.setOnLoadMoreListener(this);

        initData();
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            mData.add("测试数据 = " + i);
        }

//        setData(mData);
    }

    private void setData(List<String> data) {
        mData = data;
        Log.e("返回的数据为 " , data.toString());
        LogUtils.loge("返回的数据为 " + data.toString());
        if (myAdapter.getPageBean().isRefresh()) {
            iRecyclerView.setRefreshing(false);
            myAdapter.replaceAll(data);
        } else {
            if (data.size() > 0) {
                iRecyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.GONE);
                myAdapter.addAll(data);
            } else {
                iRecyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.THE_END);
            }
        }
    }


    @Override
    public void onRefresh() {
        myAdapter.getPageBean().setRefresh(true);
        currentPage = 1;
        iRecyclerView.setRefreshing(true);
        initData();
    }

    @Override
    public void onLoadMore(View loadMoreView) {
        myAdapter.getPageBean().setRefresh(false);
        //发起请求
        iRecyclerView.setLoadMoreStatus(LoadMoreFooterView.Status.LOADING);
        currentPage = currentPage + 1;
        initData();
    }
}
