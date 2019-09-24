package com.warriorminds.comments.data

import com.warriorminds.comments.domain.RedditContent
import com.warriorminds.comments.domain.RedditData
import com.warriorminds.comments.domain.RedditResponse
import rx.Observable
import rx.functions.Func1

class RedditDataParser : Func1<RedditResponse, Observable<RedditContent>> {

    public var after: String? = null

    override fun call(redditResponse : RedditResponse?) : Observable<RedditContent> {
        return Observable
                .just(redditResponse?.data)
                .flatMap { redditData: RedditData? ->
                    this.after = redditData?.after
                    if (redditData == null) {
                        Observable.empty()
                    } else {
                        Observable.from(redditData.children)
                    }
                }

    }
}