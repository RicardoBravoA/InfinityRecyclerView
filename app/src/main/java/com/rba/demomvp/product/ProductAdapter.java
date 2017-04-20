package com.rba.demomvp.product;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.rba.demomvp.R;
import com.rba.demomvp.component.recyclerview.InfinityRecyclerView;
import com.rba.demomvp.model.response.PageResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ricardo Bravo on 6/03/17.
 */

public class ProductAdapter extends InfinityRecyclerView<RecyclerView.ViewHolder> {

    private List<PageResponse.DataBean> pageDataBeanList;
    static LayoutInflater inflater = null;
    private ProductView productView;
    private Context context;

    public ProductAdapter(Context context, ProductView productView){
        this.context = context;
        this.productView = productView;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        pageDataBeanList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder getLoadingViewHolder(ViewGroup parent) {
        View loadingView = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
        return new LoadingViewHolder(loadingView);
    }

    @Override
    public int setVisibleThreshold() {
        return 7;
    }


    @Override
    public int getCount() {
        return pageDataBeanList.size();
    }

    @Override
    public int getViewType(int position) {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View dummyView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new PokemonViewHolder(dummyView);
    }

    void load(List<PageResponse.DataBean> pageDataBeanList){
        this.pageDataBeanList = pageDataBeanList;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        if (holder instanceof PokemonViewHolder) {
            PokemonViewHolder itemHolder = (PokemonViewHolder) holder;
            PageResponse.DataBean resultsBean = pageDataBeanList.get(position);
            itemHolder.lblDescription.setText(resultsBean.getDescription().toUpperCase());
        }

    }

    class PokemonViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lblDescription) AppCompatTextView lblDescription;

        PokemonViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.cvGeneral)
        void onClickGeneral(){
            productView.showMessage(pageDataBeanList.get(getAdapterPosition()).getDescription());
        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {

         @BindView(R.id.pbLoading) ProgressBar pbLoading;

        LoadingViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
