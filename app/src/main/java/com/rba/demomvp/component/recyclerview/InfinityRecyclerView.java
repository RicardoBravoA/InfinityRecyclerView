package com.rba.demomvp.component.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Ricardo Bravo on 7/03/17.
 */

public abstract class InfinityRecyclerView<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_LOADING = 0;

    private boolean mShouldLoadMore = true;
    private boolean mIsLoading = false;
    private boolean mIsReversedScrolling = false;
    private OnLoadMoreListener mLoadMoreListener;

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_LOADING) {
            return getLoadingViewHolder(parent);
        } else {
            return onCreateView(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mShouldLoadMore && !mIsLoading) {
            int threshold = setVisibleThreshold();
            boolean hasReachedThreshold = mIsReversedScrolling ? position <= threshold
                    : position >= getCount() - threshold;
            if (hasReachedThreshold) {
                mIsLoading = true;
                if (mLoadMoreListener != null) {
                    mLoadMoreListener.onLoadMore();
                }
            }
        }
    }

    @Override
    public final int getItemCount() {
        int actualCount = getCount();

        if(actualCount == 0 || !mShouldLoadMore || mIsReversedScrolling) {
            return actualCount;
        } else {
            return actualCount + 1;
        }
    }

    @Override
    public final int getItemViewType(int position) {
        if(isLoadingView(position)) {
            return VIEW_TYPE_LOADING;
        } else {
            int viewType = getViewType(position);
            if (viewType == VIEW_TYPE_LOADING) {
                throw new IndexOutOfBoundsException("0 index is reserved for the loading view");
            } else {
                return viewType;
            }
        }
    }

    private boolean isLoadingView(int position) {
        int loadingViewPosition = mIsReversedScrolling ? 0 : getCount();
        return position == loadingViewPosition && mShouldLoadMore;
    }

    public void setShouldLoadMore(boolean shouldLoadMore) {
        this.mShouldLoadMore = shouldLoadMore;
    }

    public void setIsReversedScrolling(boolean reversed) {
        this.mIsReversedScrolling = reversed;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mLoadMoreListener = listener;
    }

    public void moreDataLoaded(int positionStart, int itemCount) {
        mIsLoading = false;
        notifyItemRemoved(positionStart);
        notifyItemRangeInserted(positionStart, itemCount);
    }

    public void hideLoading(){
        mIsLoading = false;
        notifyItemRemoved(getItemCount());
        setShouldLoadMore(false);
    }


    public abstract RecyclerView.ViewHolder getLoadingViewHolder(ViewGroup parent);

    public abstract int setVisibleThreshold();

    public abstract int getCount();

    public abstract int getViewType(int position);

    public abstract VH onCreateView(ViewGroup parent, int viewType);
}