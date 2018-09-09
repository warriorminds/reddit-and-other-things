package com.warriorminds.redditsampleapp.models

data class RedditListingResponse(val kind: String,
                                 val data: RedditListingData)

data class RedditListingData(val modhash: String,
                             val dist: Int,
                             val children: List<RedditListingChild>,
                             val after: String?,
                             val before: String?)

data class RedditListingChild(val kind: String,
                              val data: RedditItem)

data class RedditItem(val author: String,
                      val title: String,
                      val num_comments: String,
                      val created: Long,
                      val thumbnail: String,
                      val url: String)
