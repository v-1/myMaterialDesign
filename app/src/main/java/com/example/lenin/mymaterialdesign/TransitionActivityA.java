package com.example.lenin.mymaterialdesign;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class TransitionActivityA extends AppCompatActivity {

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_a);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Get and setting images on the grid
        try {
            ImageView ivGrid1 = (ImageView) findViewById(R.id.ivGrid1);
            assert ivGrid1 != null;
            Glide.with(this).load(R.drawable.grid1).fitCenter().centerCrop().into(ivGrid1);

            ImageView ivGrid2 = (ImageView) findViewById(R.id.ivGrid2);
            assert ivGrid2 != null;
            Glide.with(this).load(R.drawable.grid2).fitCenter().into(ivGrid2);

            ImageView ivGrid3 = (ImageView) findViewById(R.id.ivGrid3);
            assert ivGrid3 != null;
            Glide.with(this).load(R.drawable.grid3).fitCenter().crossFade().centerCrop().into(ivGrid3);

            ImageView ivGrid4 = (ImageView) findViewById(R.id.ivGrid4);
            assert ivGrid4 != null;
            Glide.with(this).load(R.drawable.grid4).fitCenter().centerCrop().into(ivGrid4);

            ImageView ivGrid5 = (ImageView) findViewById(R.id.ivGrid5);
            assert ivGrid5 != null;
            Glide.with(this).load(R.drawable.grid5).fitCenter().centerCrop().into(ivGrid5);

            ImageView ivGrid6 = (ImageView) findViewById(R.id.ivGrid6);
            assert ivGrid6 != null;
            Glide.with(this).load(R.drawable.grid6).fitCenter().centerCrop().into(ivGrid6);

        }catch(Exception e){
           Log.e("TransitionActivityA",e.getMessage());
        }
        //Setting on click actions for the grid's child
        GridLayout gl = (GridLayout) findViewById(R.id.GridLayout);
        assert gl != null;
        for (int i = 0; i < gl.getChildCount(); i++) {
            View v = gl.getChildAt(i);
            if (v instanceof ImageView) {
                final int position = i;
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivityB((ImageView) v, position);
                    }
                });
            }
        }
        //Setting the Snack Bar for the Floating Action Button
        fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "will can be an action here", Snackbar.LENGTH_LONG)
                            .setAction("not", null).show();
                }
            });
        }
        //Transition to enter from Main Activity
        Explode enterTransition=new Explode();
        enterTransition.excludeTarget(fab,true);
        enterTransition.setDuration(500);
        getWindow().setEnterTransition(enterTransition);
        //Transition to come back to Main Activity
        TransitionSet returnTransition=new TransitionSet();
        Explode explodeReturn=new Explode();
        explodeReturn.excludeTarget(gl.getChildAt(1),true);
        returnTransition.addTransition(explodeReturn);
        returnTransition.addTransition(new Fade());
        returnTransition.setDuration(500);
        getWindow().setReturnTransition(returnTransition);
        //Transition to go to the Activity B
        Slide slide=new Slide(Gravity.TOP);
        slide.setDuration(500);
        getWindow().setExitTransition(slide);
        //Transition to come back for Activity B
        Explode reEnteredTransition=new Explode();
        reEnteredTransition.setDuration(500);
        getWindow().setReenterTransition(reEnteredTransition);
        //set listeners to manage FloatingActionButton animation and visibility
        getWindow().getExitTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                CircleRevealAnimation.exitCircleReveal(fab);
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
        getWindow().getEnterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
            }

            @Override
            public void onTransitionEnd(Transition transition) {
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
        getWindow().getReenterTransition().addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
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


    }
    /**
     * <h1>Start Activity B</h1>
     * <p>Start a new Activity with a shared element transition (<b>v<b/>) and send a bundle with the
     * name of the image (<b>grid+i<b/>) witch will be loaded in the new activity.
     * </p>
     * @param v view that will be shared in the transition
     * @param i position of the View in the Grid
     */
    private void startActivityB(ImageView v, int i) {
        Intent intent = new Intent(this.getBaseContext(), TransitionActivityB.class);
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, v, v.getTransitionName());
        intent.putExtra("src", "grid" + (i + 1));
        startActivity(intent, options.toBundle());
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
