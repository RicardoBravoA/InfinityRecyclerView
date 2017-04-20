package com.rba.demomvp.product;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rba.demomvp.R;
import com.rba.demomvp.component.recyclerview.OnLoadMoreListener;
import com.rba.demomvp.model.response.PageResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductActivity extends AppCompatActivity implements ProductView, OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.rcvGeneral) RecyclerView rcvGeneral;
    @BindView(R.id.swrGeneral) SwipeRefreshLayout swrGeneral;
    private ProductAdapter productAdapter;
    private ProductPresenter pokemonPresenter;
    private List<PageResponse.DataBean> pageDataBeanList = new ArrayList<>();
    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        init();
    }

    @Override
    public void init() {
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        productAdapter = new ProductAdapter(this, this);
        pokemonPresenter = new ProductPresenter(this);

        rcvGeneral.setLayoutManager(new LinearLayoutManager(this));
        rcvGeneral.setHasFixedSize(true);
        rcvGeneral.setItemAnimator(new DefaultItemAnimator());
        rcvGeneral.setAdapter(productAdapter);
        productAdapter.setOnLoadMoreListener(this);
        productAdapter.load(pageDataBeanList);
        pokemonPresenter.page(page);

        swrGeneral.setOnRefreshListener(this);


    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        productAdapter.hideLoading();
    }

    @Override
    public void showResult(PageResponse pageResponse) {

        hideRefresh();
        int currentSize = pageDataBeanList.size();
        Log.i("z- pageResponse", new Gson().toJson(pageResponse));

        pageDataBeanList.addAll(pageResponse.getData());
        productAdapter.moreDataLoaded(currentSize, pageDataBeanList.size() - currentSize);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRefresh() {
        if(!swrGeneral.isRefreshing()){
            swrGeneral.setRefreshing(true);
        }
    }

    @Override
    public void hideRefresh() {
        if(swrGeneral.isRefreshing()){
            swrGeneral.setRefreshing(false);
        }
    }

    @Override
    public void onLoadMore() {
        Log.i("z- loadMore", "true");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                page++;
                pokemonPresenter.page(page);
            }
        }, 1000);


    }

    @Override
    public void onRefresh() {
        page = 1;
        if(!pageDataBeanList.isEmpty()){
            pageDataBeanList.clear();
            productAdapter.notifyDataSetChanged();
        }

        pokemonPresenter.page(page);
    }
}
