package com.testes.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {

	float xDistance, yDistance=0;
	float lastX, lastY;
	
    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFadingEdgeLength(0);
    }

	@Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

    	switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:
            xDistance = yDistance = 0f;
            lastX = ev.getX();
            lastY = ev.getY();
            break;
        case MotionEvent.ACTION_MOVE:
            final float curX = ev.getX();
            final float curY = ev.getY();
            xDistance += Math.abs(curX - lastX);
            yDistance += Math.abs(curY - lastY);
            lastX = curX;
            lastY = curY;
            //if the distance in the x axis was bigger than the distance on the y axis, it passes the event to the child
            if(xDistance > yDistance){
                return false;  
            }
    	}
    	return super.onInterceptTouchEvent(ev);
	}
   }