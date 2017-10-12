package com.ikt.main.to;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.ikt.main.to.model.DBHelper;
import com.ikt.main.to.activities.LoginActivity;
import com.ikt.main.to.activities.NotifDetailActivity;
import com.ikt.main.to.activities.StatusTicketDetailActivity;
import com.ikt.main.to.activities.TruckActivitiesDetailActivity;
import com.ikt.main.to.object.NotifObject;
import com.ikt.main.to.util.Config;

/**
 * Created by Arifin on 5/1/16.
 */
public class GCMIntentService extends GCMBaseIntentService {

    private static final String TAG = "GCMIntentService";
    private IKTApplication aController = null;

    public GCMIntentService() {
        super(Config.CLIENT_ID);
    }

    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    private static void generateNotification(Context context, String message) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = null;//
        int n = 1;

        String[] messages = message.split("#");
        int code = Integer.parseInt(messages[0]);
        String info = "";
        String truckWithoutSpaces = "";
        switch (code) {
            case 10:
                info = "Entry Ticket : " + messages[1] + " - " + messages[2];
                notificationIntent = new Intent(context, StatusTicketDetailActivity.class);
                notificationIntent.putExtra("visitId", messages[2]);
                notificationIntent.putExtra("isAssign", false);
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
            case 20:
                info = "Unassigned Ticket : " + messages[1] + " - " + messages[2];
                notificationIntent = new Intent(context, StatusTicketDetailActivity.class);
                notificationIntent.putExtra("visitId", messages[2]);
                notificationIntent.putExtra("isAssign", true);
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
            case 31:
                info = "Status Update : " + messages[1] + " " + messages[2];
                notificationIntent = new Intent(context, TruckActivitiesDetailActivity.class);
                notificationIntent.putExtra("visitId", messages[4]);
                truckWithoutSpaces = messages[1].replaceAll("\\s+","");
                notificationIntent.putExtra("truck", truckWithoutSpaces);
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
            case 32:
                info = "Status Update : " + messages[1] + " " + messages[2];
                notificationIntent = new Intent(context, TruckActivitiesDetailActivity.class);
                notificationIntent.putExtra("visitId", messages[4]);
                truckWithoutSpaces = messages[1].replaceAll("\\s+","");
                notificationIntent.putExtra("truck", truckWithoutSpaces);
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
            case 33:
                info = "Status Update : " + messages[1] + " " + messages[2];
                notificationIntent = new Intent(context, TruckActivitiesDetailActivity.class);
                notificationIntent.putExtra("visitId", messages[4]);
                truckWithoutSpaces = messages[1].replaceAll("\\s+","");
                notificationIntent.putExtra("truck", truckWithoutSpaces);
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
            case 40:
                info = messages[1] + " : pengumuman";
                notificationIntent = new Intent(context, NotifDetailActivity.class);
                notificationIntent.putExtra("msg", messages[2]);
                notificationIntent.putExtra("isNotif", true);
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
            case 50:
                info = "Broadcast : " + messages[2];
                notificationIntent = new Intent(context, NotifDetailActivity.class);
                notificationIntent.putExtra("msg", messages[2]);
                notificationIntent.putExtra("msgId", messages[1]);
                notificationIntent.putExtra("isNotif", true);
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
            case 90:
                info = "Error : " + messages[2];
                notificationIntent = new Intent(context, NotifDetailActivity.class);
                notificationIntent.putExtra("msg", messages[2]);
                notificationIntent.putExtra("isNotif", true);
                notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                break;
        }

        PendingIntent intent = PendingIntent.getActivity(context, 99, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        int icon = R.drawable.ikt_logo_putih;
        long when = System.currentTimeMillis();
        String sender = context.getString(R.string.app_name);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            Notification noti = new Notification.Builder(context)
                    .setContentTitle(sender)
                    .setContentText(info)
                    .setSmallIcon(R.drawable.ikt_logo_putih)
                    .setWhen(when)
                    .setContentIntent(intent)
                    .setAutoCancel(false)
                    .setPriority(Notification.PRIORITY_HIGH)
                    .build();

            notificationManager.notify(n, noti);
        } else {
            Notification notification = new Notification(icon, message, when);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            // Play default notification sound
            notification.defaults |= Notification.DEFAULT_SOUND;
            notification.contentIntent = intent;
            notification.icon = icon;

            // Vibrate if vibrate is enabled
            notification.defaults |= Notification.DEFAULT_VIBRATE;
            notificationManager.notify(n, notification);
        }
    }

    /**
     * Method called on device registered
     **/
    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.i(TAG, "Device registered: regId = " + registrationId);
        //Get Global Controller Class object (see application tag in AndroidManifest.xml)
        if (aController == null)
            aController = IKTApplication.getInstance();

        Log.i(TAG, "Device registered: regId = " + registrationId);
//        aController.displayMessageOnScreen(context,"Your device registred with GCM");
        aController.register(context, LoginActivity.name, LoginActivity.email, registrationId);
    }

    /**
     * Method called on device un registred
     */
    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "Device unregistered");
        if (aController == null)
            aController = IKTApplication.getInstance();
        Log.i(TAG, "Device unregistered");
        aController.unregister(context, registrationId);
    }

    /**
     * Method called on Receiving a new message
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void onMessage(Context context, Intent intent) {
        Log.i(TAG, "Received message");
        if (aController == null)
            aController = IKTApplication.getInstance();

        Log.i(TAG, "Received message");
        String message = intent.getExtras().getString("message");
        String[] messages = message.split("#");
        int code = Integer.parseInt(messages[0]);
        NotifObject notifObject = new NotifObject();
        notifObject.setMessage(message);
        notifObject.setType(code);
        DBHelper db = new DBHelper(context);
        db.insertNotif(notifObject);
        // notifies user
        db.closeDB();
        generateNotification(context, message);
    }

    /**
     * Method called on receiving a deleted message
     */
    @Override
    protected void onDeletedMessages(Context context, int total) {
        Log.i(TAG, "Received deleted messages notification");
        if (aController == null)
            aController = IKTApplication.getInstance();

        Log.i(TAG, "Received deleted messages notification");
        String message = "push delete";
        // notifies user
        generateNotification(context, message);
    }

    /**
     * Method called on Error
     */
    @Override
    public void onError(Context context, String errorId) {
        Log.i(TAG, "Received error: " + errorId);
        if (aController == null)
            aController = IKTApplication.getInstance();

        Log.i(TAG, "Received error: " + errorId);
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        // log message
        if (aController == null)
            aController = IKTApplication.getInstance();

        // log message
        Log.i(TAG, "Received recoverable error: " + errorId);
        return super.onRecoverableError(context, errorId);
    }

}
