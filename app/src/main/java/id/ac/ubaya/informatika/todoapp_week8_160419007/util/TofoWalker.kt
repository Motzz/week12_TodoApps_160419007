package id.ac.ubaya.informatika.todoapp_week8_160419007.util

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class TofoWalker(val context: Context,val params:WorkerParameters):Worker(context,params) {
    override fun doWork(): Result {
        //notif pemanggilan week 12
        NotificationHelper(context)
            .createNotification(inputData.getString("title").toString(),inputData.getString("message").toString())

        return Result.success()
    }
}