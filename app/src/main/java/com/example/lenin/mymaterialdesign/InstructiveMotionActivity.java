package com.example.lenin.mymaterialdesign;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.ScrollView;
import com.bumptech.glide.Glide;

public class InstructiveMotionActivity extends AppCompatActivity {

    private ScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructive_motion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        mScrollView=(ScrollView)findViewById(R.id.myScroll);
        ImageView ivHeader = (ImageView) findViewById(R.id.header_image);
        assert ivHeader != null;
        Glide.with(this).load(R.drawable.grid1).fitCenter().centerCrop().into(ivHeader);
    }
    @Override
    public void onEnterAnimationComplete(){
        super.onEnterAnimationComplete();
        Interpolator interpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.fast_out_slow_in);
        final int startScrollPos=getResources().getDimensionPixelSize(R.dimen.init_scroll_up_distance);
        Animator animator= ObjectAnimator.ofInt(mScrollView,"scrollY",startScrollPos).setDuration(1200);
        animator.setInterpolator(interpolator);
        animator.start();





    }
}
