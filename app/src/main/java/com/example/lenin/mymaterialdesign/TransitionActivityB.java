package com.example.lenin.mymaterialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class TransitionActivityB extends AppCompatActivity {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_b);

        //Set ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);

        //Set Header Image
        ImageView iv = (ImageView) findViewById(R.id.imageTransition);
        Intent intent = getIntent();
        String imageName = intent.getStringExtra("src");
        int resourceID = this.getResources().getIdentifier(imageName, "drawable",this.getPackageName());
        assert iv != null;
        Glide.with(this).load(resourceID).fitCenter().into(iv);

        //Set Floating Action Button
        fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "will can be an action here", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //Transition to enter from Activity A
        Explode enterTransition=new Explode();
        enterTransition.setDuration(500);
        getWindow().setEnterTransition(enterTransition);
        //Transition to return to Activity A
        Explode returnTransition=new Explode();
        returnTransition.excludeTarget(fab,true);
        returnTransition.setDuration(500);
        getWindow().setReturnTransition(returnTransition);
        //set listeners to manage FloatingActionButton animation and visibility
        getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                actionBar.setDisplayShowTitleEnabled(true);
                CircleRevealAnimation.enterCircleReveal(fab);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }

        });
        getWindow().getSharedElementReturnTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                actionBar.setDisplayShowTitleEnabled(false);
                fab.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionEnd(Transition transition) {

            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
