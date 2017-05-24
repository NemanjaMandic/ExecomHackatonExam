package com.example.nemus.execomhackaton.util;

import android.content.Context;
import android.widget.EditText;

/**
 * Created by nemus on 23-May-17.
 */

public class InputValidation {
    private Context context;

    public InputValidation(Context context){
        this.context=context;
    }

    public boolean isLoginInputEditTextFilled(EditText editText, EditText editText2, String message){
        String value1 = editText.getText().toString().trim();
        String value2 = editText2.getText().toString().trim();
        if(value1.isEmpty() || value2.isEmpty()){
            ToastUtil.shortToast(context, message);
            return false;
        }else{
            return true;
        }
    }

    public boolean isEmptyField(EditText input, String msg){
        String value = input.getText().toString();
        if(value.isEmpty()){
            ToastUtil.shortToast(context, msg);
            return false;
        }else{
            return true;
        }
    }



}
