package com.sanalkasif.dailyhoroscopeapp.helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import com.sanalkasif.dailyhoroscopeapp.R;

import java.util.ArrayList;

/**
 * Created by erdi on 1/2/2017.
 */

public class CircularProgress {

    public ArrayList<ProgressDialog> spinner_dialog_list;
    public static CircularProgress instance;
    private ProgressDialog dialog;
    private Context context;

    public CircularProgress(Context context){
        spinner_dialog_list = new ArrayList<>();
        this.context = context;
    }
    public static CircularProgress getInstance(Context context) {
        if(instance == null) {
            instance = new CircularProgress(context);
        }
        return instance;
    }
    public void ShowCircularProgress(){

        if(context != null && !((Activity) context).isFinishing()) {
            dialog = new ProgressDialog(context);
            dialog.setCancelable(false);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
            wmlp.y = Gravity.CENTER_VERTICAL;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            dialog.setContentView(R.layout.spinner_layout);
            spinner_dialog_list.add(dialog);
                RotateAnimation rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                rotate.setDuration(2000);
                rotate.setRepeatCount(200);
                rotate.setInterpolator(new LinearInterpolator());
                ImageView logo_inside = (ImageView) dialog.findViewById(R.id.bg_logo);
                logo_inside.startAnimation(rotate);

        }
    }

    public void DismissCircularProgress(){

        try {
            if ((dialog != null) && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (final IllegalArgumentException e) {
            // Handle or log or ignore
        } catch (final Exception e) {
            // Handle or log or ignore
        } finally {
            dialog = null;
            instance = null;
        }
    }
    public boolean isShowing(){
        if(dialog != null)
            return dialog.isShowing();
        return false;
    }
}
