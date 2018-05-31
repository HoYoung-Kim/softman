package com.jerryfamily.mymuseumtrip;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by 호영 on 2018-05-04.
 */

public class AlertDialogUtil {

    public static AlertDialog singleErrorButton(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("입력 오류");
        builder.setMessage("내용을 입력해 주세요.");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_error_alert);
        builder.setPositiveButton("확  인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    public static AlertDialog singleSearchErrorButton(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("입력 오류");
        builder.setMessage("정확한 검색을 위해 2글자 이상 입력해 주세요.");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_error_alert);
        builder.setPositiveButton("확  인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    public static AlertDialog singleButton(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context,
        AlertDialog.THEME_HOLO_DARK);
        builder.setTitle("My Alert Title")
                .setIcon(R.drawable.ic_action_error_alert)
                .setCancelable(false)
                .setMessage("This demo is for you.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }

    public static AlertDialog doubleButton(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context,
        AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
        builder.setTitle("My Alert Title")
                .setIcon(R.drawable.ic_action_error_alert)
                .setCancelable(false)
                .setMessage("Are you happy?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        Toast toast = Toast.makeText(context, "You are a HAPPY person.", Toast.LENGTH_LONG);
                        toast.show();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        Toast toast = Toast.makeText(context, "You are a SAD person.", Toast.LENGTH_LONG);
                        toast.show();
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        return alertDialog;
    }
}
