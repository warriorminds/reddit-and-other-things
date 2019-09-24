package com.warriorminds.comments.adapter

import com.warriorminds.comments.ExpandableCommentItem
import com.warriorminds.comments.domain.Comment
import com.xwray.groupie.ExpandableGroup

class ExpandableCommentGroup constructor(
        comment : Comment,
        depth : Int = 0) : ExpandableGroup(ExpandableCommentItem(comment, depth)) {

    init {
        for (comm in comment.children) {
            add(ExpandableCommentGroup(comm, depth.plus(1)))
        }
    }

}