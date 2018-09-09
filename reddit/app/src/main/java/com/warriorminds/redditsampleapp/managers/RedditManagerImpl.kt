package com.warriorminds.redditsampleapp.managers

import com.warriorminds.redditsampleapp.api.RedditRetrofitService
import com.warriorminds.redditsampleapp.models.RedditListingChild
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RedditManagerImpl @Inject constructor(val service: RedditRetrofitService) : RedditManager {

    private val compositeDisposable = CompositeDisposable()

    override fun getTop(callback: (List<RedditListingChild>, String?) -> Unit,
                        error: (Throwable) -> Unit, limit: Int, after: String) {
        compositeDisposable.add(
                service.getTop(limit, after)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { response -> callback(response.data.children, response.data.after) },
                                { error -> error(error) })
        )
    }

    override fun cleanUp() {
        compositeDisposable.clear()
    }
}