package com.warriorminds.comments.ui.singlePost

import com.warriorminds.comments.domain.Comment
import com.warriorminds.comments.ui.custom.BasePresenter
import com.warriorminds.comments.ui.custom.BaseView

interface PostContractor {

    interface View : BaseView<Presenter> {

        fun showInfo()
        fun showComment(comment : Comment)

    }

    interface Presenter : BasePresenter {

        fun loadInfo(permalink: String)

    }

}