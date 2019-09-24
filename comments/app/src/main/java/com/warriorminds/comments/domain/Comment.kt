package com.warriorminds.comments.domain

data class Comment constructor(val body : String,
                               val author : String,
                               val score : Int,
                               val id : String,
                               val children : List<Comment>)