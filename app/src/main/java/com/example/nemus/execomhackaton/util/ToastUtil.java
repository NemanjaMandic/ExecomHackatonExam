package com.example.nemus.execomhackaton.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by nemus on 17-May-17.
 */

public final class ToastUtil {


    public static void shortToast(Context context, String msg){

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(Context context, String msg){

        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
