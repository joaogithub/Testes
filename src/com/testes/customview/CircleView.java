package com.testes.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CircleView extends View {
    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    
    private void init(Context context) {
        //do stuff that was in your original constructor...
    }

    
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(150);
        canvas.drawCircle(50,50,20,paint);
    }
}
