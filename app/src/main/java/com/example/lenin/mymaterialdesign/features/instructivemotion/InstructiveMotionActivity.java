package com.example.lenin.mymaterialdesign.features.instructivemotion;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.ScrollView;
import com.bumptech.glide.Glide;
import com.example.lenin.mymaterialdesign.R;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public class InstructiveMotionActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.myScroll)
    ScrollView mScrollView;
    @BindView(R.id.header_image)
    ImageView ivHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructive_motion);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Glide.with(this).load(R.drawable.grid1).fitCenter().centerCrop().into(ivHeader);
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar){
        super.setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
    }

    @Override
    public void onEnterAnimationComplete(){
        super.onEnterAnimationComplete();
        Interpolator interpolator = AnimationUtils.loadInterpolator(this, android.R.interpolator.fast_out_slow_in);
        final int startScrollPos = getResources().getDimensionPixelSize(R.dimen.init_scroll_up_distance);
        Animator animator= ObjectAnimator.ofInt(mScrollView,"scrollY",startScrollPos).setDuration(1200);
        animator.setInterpolator(interpolator);
        animator.start();





    }
}
