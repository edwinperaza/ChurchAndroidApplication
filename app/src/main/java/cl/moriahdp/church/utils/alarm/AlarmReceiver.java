package cl.moriahdp.church.utils.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cl.moriahdp.church.SecretApplication;
import cl.moriahdp.church.main.activities.DashboardActivity;


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                // Set the alarm here.
                NotificationScheduler.setReminder(context,
                        AlarmReceiver.class,
                        SecretApplication.getInstance().getTinyDB().getInt("hour"),
                        SecretApplication.getInstance().getTinyDB().getInt("min"));
                return;
            }
        }

        if (context != null) {
            NotificationScheduler.showNotification(context, DashboardActivity.class, "Titulo", "Mensaje");
        }

    }
}
