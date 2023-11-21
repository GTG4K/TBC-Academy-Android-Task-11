package com.example.task11.data

import com.example.task11.R

data class Image(
    val id: Int,
    val image: Int,
    val title: String,
    val description: String
) {
    companion object {
        fun getImageData(): MutableList<Image> {
            return mutableListOf<Image>().apply {
                add(Image(1, R.drawable.rem, "rem", "picture of rem"))
                add(Image(2, R.drawable.rem2, "rem2", "another picture of rem"))
                add(Image(3, R.drawable.tanjiro1, "tanjiro", "picture of tanjiro"))
                add(Image(4, R.drawable.tanjoro2, "tanjiro2", "another picture of tanjiro"))
            }
        }
    }
}
