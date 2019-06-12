package com.s.d.a.a.conceitodashboard_v2_com_fragmentos.util;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;

import com.s.d.a.a.androidutils.Utilitaria;
import com.s.d.a.a.conceitodashboard_v2_com_fragmentos.R;

public class ActionBarUtil {
    public static void setBackgroundImage(Activity activity) {
        if(Utilitaria.isAndroid_3()) {
            ActionBar actionBar = activity.getActionBar();
            if(actionBar != null) {
                Resources res = activity.getResources();
                actionBar.setBackgroundDrawable(res.getDrawable(R.drawable.shape_header));
            }
        }
    }
    public static void setActionBarText(Activity activity,String s) {
        if(Utilitaria.isAndroid_3()) {
            ActionBar actionBar = activity.getActionBar();
            if(actionBar != null) {
                actionBar.setTitle(s);
            }
        }
    }
}
