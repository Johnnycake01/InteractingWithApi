package com.example.enqueuepractice.ui

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

/**
 * class HorizontalMaginDecorationForViewPager adds margin to the left and right sides of the View item.
 * @param horizontalMarginInDp is the margin resource in dp.
 * the horizontalMarginInDp is passed as parameter for the class
 * context is the this -> which denote the class you are working on
 * it extends RecyclerView with static method ItemDecoration which allows properties to be
 * set for view decoration
 */
class HorizontalMaginDecorationForViewPager (context: Context, @DimenRes horizontalMarginInDp: Int) :
    RecyclerView.ItemDecoration() {

    private val horizontalMarginInPx: Int =
        context.resources.getDimension(horizontalMarginInDp).toInt()

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        outRect.right = horizontalMarginInPx
        outRect.left = horizontalMarginInPx
    }

}