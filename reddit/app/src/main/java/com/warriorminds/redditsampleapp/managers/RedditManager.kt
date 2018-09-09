package com.warriorminds.redditsampleapp.managers

import com.warriorminds.redditsampleapp.models.RedditListingChild

interface RedditManager {
    fun getTop(callback: (List<RedditListingChild>, String?) -> Unit,
               error: (Throwable) -> Unit,
               limit: Int = 10, after: String = "")

    fun cleanUp()
}