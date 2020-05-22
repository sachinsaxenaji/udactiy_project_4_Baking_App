package com.example.android.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;


@RunWith(AndroidJUnit4.class)
public class SelectRecipeBasicTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    //Testing click on first item to load detail activity
    @Test
    public void clickRecipe_StartsLoadsRecipeDetailActivity(){

        onView(withId(R.id.rv_recipeList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.ing_step_lv)).check(matches((isDisplayed())));


    }

    //Testing click on first button on Step list  to load STEP detail activity
    @Test
    public void afterRecipeDetailActivityDisplayed_clickOnStepButtonLoadsStepDetailActivity(){

        onView(withId(R.id.rv_recipeList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.ing_step_lv)).check(matches((isDisplayed())));
        onView(allOf(withId(R.id.btn_step), withText("RECIPE INTRODUCTION"))).perform(click());
        onView(withId(R.id.sv_step_detail_view)).check(matches((isDisplayed())));


    }




    //Testing click on Next Button
    @Test
    public void afterStepDetailActivityDisplayed_ClickOnNextStep(){

        onView(withId(R.id.rv_recipeList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.ing_step_lv)).check(matches((isDisplayed())));
        onView(allOf(withId(R.id.btn_step), withText("RECIPE INTRODUCTION"))).perform(click());
        onView(withId(R.id.sv_step_detail_view)).check(matches((isDisplayed())));
        onView(withId(R.id.sv_step_detail_view)).perform( swipeUp());
        onView(allOf(withId(R.id.btn_next_step), withText("Next Step"))).perform(click());

    }



    //Testing click on Previous Button
    @Test
    public void afterStepDetailActivityDisplayed_ClickOnPreviousStep(){

        onView(withId(R.id.rv_recipeList)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.ing_step_lv)).check(matches((isDisplayed())));
        onView(allOf(withId(R.id.btn_step), withText("STARTING PREP"))).perform(click());
        onView(withId(R.id.sv_step_detail_view)).check(matches((isDisplayed())));
        onView(withId(R.id.sv_step_detail_view)).perform( swipeUp());
        onView(allOf(withId(R.id.btn_prev_step), withText("Previous Step"))).perform(click());

    }





}
