package com.example.lenin.mymaterialdesign.features.transitions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.ArrayMap;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.lenin.mymaterialdesign.utils.CircleRevealAnimation;
import com.example.lenin.mymaterialdesign.R;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public class TransitionActivityB extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.imageTransition)
    ImageView iv;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_b);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTransitionImage();

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

    private void setTransitionImage(){
        Intent intent = getIntent();
        String imageName = intent.getStringExtra("src");
        int resourceID = this.getResources().getIdentifier(imageName, "drawable",this.getPackageName());
        Glide.with(this).load(resourceID).fitCenter().into(iv);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finishAfterTransition();
                fab.setVisibility(View.INVISIBLE);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause(){
        super.onPause();
        fab.setOnClickListener(null);
    }

    @Override
    protected void onResume(){
        super.onResume();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "will can be an action here", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onDestroy(){
        getWindow().getSharedElementEnterTransition().addListener(null);
        getWindow().getSharedElementReturnTransition().addListener(null);
        fab.clearAnimation();
        removeActivityFromTransitionManager(this);
        super.onDestroy();
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
    public void onBackPressed() {
        fab.setVisibility(View.INVISIBLE);
        super.onBackPressed();
    }
    //Todo: remove workaround to avoid leak on transitions
    private static void removeActivityFromTransitionManager(Activity activity) {

        Class transitionManagerClass = TransitionManager.class;
        try {
            Field runningTransitionsField = transitionManagerClass.getDeclaredField("sRunningTransitions");
            runningTransitionsField.setAccessible(true);
            //noinspection unchecked
            ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>> runningTransitions
                    = (ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>>)
                    runningTransitionsField.get(transitionManagerClass);
            if (runningTransitions.get() == null || runningTransitions.get().get() == null) {
                return;
            }
            ArrayMap map = runningTransitions.get().get();
            View decorView = activity.getWindow().getDecorView();
            if (map.containsKey(decorView)) {
                map.remove(decorView);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
