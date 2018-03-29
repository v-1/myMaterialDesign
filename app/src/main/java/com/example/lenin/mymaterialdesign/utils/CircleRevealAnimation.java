package com.example.lenin.mymaterialdesign.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

public class CircleRevealAnimation {

    public static void enterCircleReveal(final View v){
        Rect bounds = new Rect();
        v.getDrawingRect(bounds);
        int cx = bounds.centerX();
        int cy = bounds.centerY();
        int finalRadius = Math.max(v.getWidth(), v.getHeight())/2;
        Animator anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finalRadius);
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
        anim.setStartDelay(200);
        anim.setDuration(400);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation){
                super.onAnimationStart(animation);
                v.setVisibility(View.VISIBLE);
            }
        });
        anim.start();
    }
    public static void exitCircleReveal(final View v){
        Rect bounds = new Rect();
        v.getDrawingRect(bounds);
        int cx = bounds.centerX();
        int cy = bounds.centerY();
        int initialRadius = Math.max(v.getWidth(), v.getHeight())/2;
        Animator anim =ViewAnimationUtils.createCircularReveal(v, cx, cy, initialRadius, 0);
        anim.setDuration(400);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                v.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }
}
