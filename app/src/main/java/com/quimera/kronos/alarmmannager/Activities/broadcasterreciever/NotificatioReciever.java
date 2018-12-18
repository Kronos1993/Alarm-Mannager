package com.quimera.kronos.alarmmannager.Activities.broadcasterreciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.quimera.kronos.alarmmannager.Notificaciones.NotificacionID;
import com.quimera.kronos.alarmmannager.Notificaciones.Notificaciones;

/**
 * Created by farah on 22/sep/2018.
 */
public class NotificatioReciever extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Notificaciones.createNotification(context, NotificacionID.alarm, intent);
    }
}
