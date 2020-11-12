package com.apurba.testapp.util;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import com.apurba.testapp.R;


public class AnimationUtility {




    public static Animation getSlideUpAnimation(Context context){
        if (context != null){
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            animation.setDuration(500);
            return animation;
        }
        return null;
    }

    public static Animation getSlideBottomAnimation(Context context){
        if (context != null){
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_bottom);
            animation.setDuration(1300);
            return animation;
        }
        return null;
    }

}
