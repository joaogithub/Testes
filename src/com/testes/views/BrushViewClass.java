package com.testes.views;

import com.testes.android.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;

public class BrushViewClass extends View {
    private Paint brush = new Paint();
    private Path path = new Path();
    public Button btnEraseAll;
    public LayoutParams params;

    public BrushViewClass(Context context) {
        super(context);
        brush.setAntiAlias(true);
        brush.setColor(Color.BLUE);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(10f);

        btnEraseAll = new Button(context);
        btnEraseAll.setText(context.getString(R.string.redraw_again));
        params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);

        btnEraseAll.setBackgroundDrawable(getResources().getDrawable(R.drawable.green_degradee));
        btnEraseAll.setLayoutParams(params);
        btnEraseAll.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                path.reset();
                postInvalidate();

            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        // Checks for the event that occurs
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
            path.moveTo(pointX, pointY);

            return true;
        case MotionEvent.ACTION_MOVE:
            path.lineTo(pointX, pointY);
            break;
        case MotionEvent.ACTION_UP:
            break;
        default:
            return false;
        }
        // Force a view to draw.
        postInvalidate();
        return false;

    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, brush);
    }
}