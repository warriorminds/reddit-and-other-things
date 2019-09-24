package com.warriorminds.comments.ui.singlePost

import android.os.Bundle
import android.support.v7.recyclerview.R.attr.layoutManager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.warriorminds.comments.R
import com.warriorminds.comments.R.id.rv_comments
import com.warriorminds.comments.adapter.ExpandableCommentGroup
import com.warriorminds.comments.data.store.post.PostStore
import com.warriorminds.comments.domain.Comment
import com.warriorminds.comments.network.RetrofitManager
import com.warriorminds.comments.ui.custom.BaseActivityWithPresenter
import com.xwray.groupie.GroupAdapter

class PostActivity : BaseActivityWithPresenter<PostContractor.Presenter>(), PostContractor.View {

    private val groupAdapter = GroupAdapter<RecyclerView.ViewHolder>()
    private lateinit var groupLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_post)
        setupViews()
        val postStore = PostStore(RetrofitManager.getInstance().redditApi)
        setPresenter(PostPresenter(this, postStore))
        populateAdapter()

        groupLayoutManager = GridLayoutManager(this, groupAdapter.spanCount).apply {
            spanSizeLookup = groupAdapter.spanSizeLookup
        }

        rv_comments.apply {
            layoutManager = groupLayoutManager
            adapter = groupAdapter
        }

    }

    private fun populateAdapter() {
        initData()
    }

    override fun showComment(comment: Comment) {
        groupAdapter.add(ExpandableCommentGroup(comment))
    }

    private fun initData() {
        val permalink = intent.getStringExtra(POST_PERMALINK)
        presenter.loadInfo(permalink)
    }

    private fun setupViews() {
        setToolbar()
    }

    override fun showInfo() {

    }

    companion object {
        val POST_PERMALINK = "_post_permalink"
    }
}