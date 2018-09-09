package com.warriorminds.redditsampleapp.viewmodels

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.warriorminds.redditsampleapp.managers.RedditManager
import com.warriorminds.redditsampleapp.models.RedditListingChild
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(var redditManager: RedditManager) : ViewModel() {
    private var reddits: MutableLiveData<List<RedditListingChild>> = MutableLiveData()
    private var after: String? = ""

    fun retrieveReddits(): LiveData<List<RedditListingChild>> {
        redditManager.getTop(
                { reddits, after -> resultRetrieved(reddits, after) },
                { error -> onError(error) },
                after = after!!)
        return reddits
    }

    override fun onCleared() {
        super.onCleared()
        redditManager.cleanUp()
    }

    private fun resultRetrieved(reddits: List<RedditListingChild>, after: String?) {
        this.reddits.value = reddits
        this.after = after
    }

    private fun onError(error: Throwable) {

    }
}