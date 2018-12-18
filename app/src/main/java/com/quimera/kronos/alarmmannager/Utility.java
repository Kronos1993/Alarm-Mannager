package com.quimera.kronos.alarmmannager;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import com.quimera.kronos.alarmmannager.Notificaciones.NotificacionID;
import com.quimera.kronos.alarmmannager.Notificaciones.Notificaciones;


/**
 * Created by Escarlet Escoto on 09/10/2016.
 */
public class Utility {


    private static NotificacionID notificacionID;


    /*Generic method for navigate from any class to anywhere*/
    public static void navigate(Context context, Class clazz){
        Intent intentHome = new Intent(context, clazz);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intentHome);
    }

    public static void navigateFlagNewTask(Context context, Class clazz){
        Intent intentHome = new Intent(context, clazz);
        intentHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentHome);
    }





}
