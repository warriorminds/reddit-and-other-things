package com.warriorminds.redditsampleapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.warriorminds.redditsampleapp.R
import com.warriorminds.redditsampleapp.models.RedditListingChild
import com.warriorminds.redditsampleapp.utils.getFriendlyTime
import kotlinx.android.synthetic.main.reddit_item.view.author
import kotlinx.android.synthetic.main.reddit_item.view.comments
import kotlinx.android.synthetic.main.reddit_item.view.description
import kotlinx.android.synthetic.main.reddit_item.view.image
import kotlinx.android.synthetic.main.reddit_item.view.time
import javax.inject.Inject

class RedditAdapter @Inject constructor() : RecyclerView.Adapter<RedditAdapter.ViewHolder>() {

    private var reddits: MutableList<RedditListingChild> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent)

    override fun getItemCount() = reddits.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = reddits[position]
        holder.itemView.description.text = item.data.title
        holder.itemView.author.text = item.data.author
        holder.itemView.comments.text = item.data.num_comments
        holder.itemView.time.text = item.data.created.getFriendlyTime()
        Picasso.get().load(item.data.thumbnail).into(holder.itemView.image)
    }

    fun addReddits(newReddits: List<RedditListingChild>) {
        val originalSize = reddits.size
        reddits.addAll(newReddits)
        notifyItemRangeInserted(originalSize, reddits.size)
    }

    class ViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.reddit_item, viewGroup, false))
}