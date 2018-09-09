package com.warriorminds.redditsampleapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.warriorminds.redditsampleapp.adapters.RedditAdapter
import com.warriorminds.redditsampleapp.utils.InfiniteScrollListener
import com.warriorminds.redditsampleapp.viewmodels.MainActivityViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.reddits_recyclerview
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var redditAdapter: RedditAdapter
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var viewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)

        val manager = LinearLayoutManager(this)
        reddits_recyclerview.apply {
            setHasFixedSize(true)
            layoutManager = manager
            adapter = redditAdapter
            addItemDecoration(DividerItemDecoration(context, manager.orientation))
            addOnScrollListener(InfiniteScrollListener({ loadMore() }, manager))
        }

        viewModel?.retrieveReddits()?.observe(this, Observer {
            redditAdapter.addReddits(it!!.toMutableList())
        })
    }

    private fun loadMore() {
        viewModel?.retrieveReddits()
    }
}
