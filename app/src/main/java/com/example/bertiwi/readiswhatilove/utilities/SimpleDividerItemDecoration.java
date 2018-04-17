package com.example.bertiwi.readiswhatilove.utilities;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.bertiwi.readiswhatilove.R;

/**
 * Custom item decoration para el {@link RecyclerView} de {@link com.example.bertiwi.readiswhatilove.model.Book}
 *
 * @author Bertiwi
 */
public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    /**
     * Contexto de la activity donde se usará la clase.
     * @param context contexto de la activity actual
     */
    public SimpleDividerItemDecoration(Context context){
        mDivider = ContextCompat.getDrawable(context, R.drawable.line_divider);
    }

    /**
     * Metodo para dibujar el custom divider
     *
     * @param c Canvas para dibujar
     * @param parent RecyclerView dónde dibujará el ItemDecoration
     * @param state Estado actual del RecyclerView
     */
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state){
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(c);
        }
    }
}
