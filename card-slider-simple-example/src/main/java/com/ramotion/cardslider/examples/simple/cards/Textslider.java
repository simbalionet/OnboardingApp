package com.ramotion.cardslider.examples.simple.cards;

import android.graphics.Bitmap;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramotion.cardslider.examples.simple.R;
import com.ramotion.cardslider.examples.simple.utils.DecodeBitmapTask;

public class Textslider {

    private static int viewWidth = 0;
    private static int viewHeight = 0;
    private final TextView textView ;


    public Textslider(TextView textView) {
        this.textView = textView;
        textView =(TextView) textView.findViewById(R.id.withText);
    }


    void setContent(@DrawableRes final int resId) {
        if (viewWidth == 0) {
            itemView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    itemView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                    viewWidth = itemView.getWidth();
                    viewHeight = itemView.getHeight();
                    loadBitmap(resId);
                }
            });
        } else {
            loadBitmap(resId);
        }
    }

    void clearContent() {
        if (task != null) {
            task.cancel(true);
        }
    }

    private void loadText(@DrawableRes int resId) {
        task = new DecodeBitmapTask(itemView.getResources(), resId, viewWidth, viewHeight, this);
        task.execute();
    }

    @Override
    public void onPostExecuted(char setTExt) {
        textView.setText(setTExt);
    }

}
}
