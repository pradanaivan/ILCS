package com.ikt.main.to;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.ikt.main.to.R;
import com.ikt.main.to.activities.ActivityCarousel;
import com.ikt.main.to.activities.LoginActivity;
import com.ikt.main.to.util.Config;

/**
 * Created by Arifin on 5/1/16.
 */
public class PushServices extends GCMBaseIntentService {

    private static final String TAG = "GCMIntentService";

    private IKTApplication aController = null;

    public PushServices() {
        super(Config.CLIENT_ID);
    }

    /**
     * Method called on device registered
     **/
    @Override
    protected void onRegistered(Context context, String registrationId) {

        //Get Global Controller Class object (see application tag in AndroidManifest.xml)
        if(aController == null)
            aController = IKTApplication.getInstance();

        Log.i(TAG, "Device registered: regId = " + registrationId);
        aController.displayMessageOnScreen(context,"Your device registred with GCM");
        aController.register(context, LoginActivity.name,LoginActivity.email, registrationId);
    }

    /**
     * Method called on device unregistred
     * */
    @Override
    protected void onUnregistered(Context context, String registrationId) {
        if(aController == null)
            aController = IKTApplication.getInstance();
        Log.i(TAG, "Device unregistered");
        aController.displayMessageOnScreen(context, context.getString(R.string.loading_unregistering));
        aController.unregister(context, registrationId);
    }

    /**
     * Method called on Receiving a new message from GCM server
     * */
    @Override
    protected void onMessage(Context context, Intent intent) {

        if(aController == null)
            aController = IKTApplication.getInstance();

        Log.i(TAG, "Received message");
        String message = intent.getExtras().getString("price");

        aController.displayMessageOnScreen(context, message);
        // notifies user
        generateNotification(context, message);
    }

    /**
     * Method called on receiving a deleted message
     * */
    @Override
    protected void onDeletedMessages(Context context, int total) {

        if(aController == null)
            aController = IKTApplication.getInstance();

        Log.i(TAG, "Received deleted messages notification");
        String message = "push delete";
        aController.displayMessageOnScreen(context, message);
        // notifies user
        generateNotification(context, message);
    }

    /**
     * Method called on Error
     * */
    @Override
    public void onError(Context context, String errorId) {

        if(aController == null)
            aController = IKTApplication.getInstance();

        Log.i(TAG, "Received error: " + errorId);
        aController.displayMessageOnScreen(context, "Push Error");
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {

        if(aController == null)
            aController = IKTApplication.getInstance();

        // log message
        Log.i(TAG, "Received recoverable error: " + errorId);
        aController.displayMessageOnScreen(context,"Recovery Push Error");
        return super.onRecoverableError(context, errorId);
    }

    /**
     * Create a notification to inform the user that server has sent a message.
     */
    private static void generateNotification(Context context, String message) {

        int icon = R.drawable.launcher;
        long when = System.currentTimeMillis();

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);

        String title = context.getString(R.string.app_name);

        Intent notificationIntent = new Intent(context, ActivityCarousel.class);
        // set intent so it does not start a new activity
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
//        notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;

        //notification.sound = Uri.parse("android.resource://"+ context.getPackageName()+ "your_sound_file_name.mp3");

        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(0, notification);

    }

}
