package school.ibaktor.smartshopsstudent.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import school.ibaktor.smartshopsstudent.MainActivity
import school.ibaktor.smartshopsstudent.R

class FirebaseMessagingService : FirebaseMessagingService(){

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let{
            sendNotification(it.body!!)
        }
    }

    fun sendNotification(messageBody: String){
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)

        val mySound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder =  NotificationCompat.Builder(this, getString(R.string.chanel_compras))
            .setSmallIcon(R.drawable.ic_notification_alert)
            .setContentTitle("Nuevo mensaje compra")
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(mySound)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(getString(R.string.chanel_compras), "Compras Verificadas", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0, notificationBuilder.build())
    }
}