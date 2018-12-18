package com.quimera.kronos.alarmmannager.Notificaciones;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;

import com.quimera.kronos.alarmmannager.R;
import com.quimera.kronos.alarmmannager.Activities.MainActivity;

/**
 * Created by farah on 30-abr-17.
 */
public class Notificaciones {

    private Notification notification;
    private NotificationManager notificationManager;

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }

    public void setNotificationManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    public Notificaciones() {
        this.notification = new Notification();
    }

    /***
     * Los que dicen actualizar en el nombre del metodo son para subir los datos
     * @param context
     * @param intent
     * @param notificationManager1
     * @param idNotificacion
     * @param ticker
     * @param contentTitle
     * @param contentText
     */
    public void createNotification(Context context, Intent intent, NotificationManager notificationManager1, int idNotificacion, CharSequence ticker, CharSequence contentTitle, CharSequence contentText ){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtra("notificationID", 1);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100, i, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationManager = notificationManager1;
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context).setContentIntent(pendingIntent).
                setContentText(contentText).
                setContentTitle(contentTitle).
                setTicker(ticker).
                setSmallIcon(R.drawable.ic_alarm).
                setVibrate(new long[] {100, 250, 100, 500}).
                setAutoCancel(true);

        if(sharedPreferences.getBoolean("vibrate",false)){
            notificationBuilder.setDefaults(Notification.DEFAULT_VIBRATE);
        }
        if(sharedPreferences.getBoolean("sound",false)){
            notificationBuilder.setDefaults(Notification.DEFAULT_SOUND);
        }
        notification=notificationBuilder.build();

        notificationManager.notify(idNotificacion, notification );

    }

    /*****************Para crear notificaciones*************************/

    public static void createNotification(Context context,NotificacionID notificationType,Intent intent){

        Notificaciones notificaciones= new Notificaciones();
        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificaciones.setNotificationManager(notificationManager);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        if(NotificacionID.alarm.equals(notificationType)) {
            CharSequence ticker = "Alarma";
            CharSequence contentTitle = sharedPreferences.getString("alarm_title","Titulo");
            CharSequence contentText = sharedPreferences.getString("alarm_content","Contenido");
            notificaciones.createNotification(context, intent, notificationManager, NotificacionID.alarm.ordinal(), ticker, contentTitle, contentText);
        }
    }
}
