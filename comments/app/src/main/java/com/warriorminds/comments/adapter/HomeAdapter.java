package com.warriorminds.comments.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.warriorminds.comments.adapter.base.AdapterDelegateManager;
import com.warriorminds.comments.adapter.base.DisplayableItem;
import com.warriorminds.comments.adapter.delegates.RedditBigPostDelegate;
import com.warriorminds.comments.adapter.delegates.RedditPostAdapterDelegate;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {

    private final int TYPE_REDDIT_POST = 1;
    private final int TYPE_BIG_REDDIT_POST = 2;

    private List<DisplayableItem> items;

    private AdapterDelegateManager<List<DisplayableItem>> adapterDelegateManager;

    public HomeAdapter(List<DisplayableItem> items, OnPostClickListener onPostClickListener) {
        this.items = items;
        adapterDelegateManager = new AdapterDelegateManager<>();
        adapterDelegateManager.addDelegate(new RedditBigPostDelegate(TYPE_BIG_REDDIT_POST, onPostClickListener));
        adapterDelegateManager.addDelegate(new RedditPostAdapterDelegate(TYPE_REDDIT_POST, onPostClickListener));
    }

    @Override
    public int getItemViewType(int position) {
        return adapterDelegateManager.getItemViewType(items, position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapterDelegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adapterDelegateManager.onBindViewHolder(items, holder, position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface OnPostClickListener {

        void onFavoriteClicked(int position);
        void onPostClicked(int position);

    }

}
