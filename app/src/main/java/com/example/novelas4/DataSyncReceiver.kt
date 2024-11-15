package com.example.novelas4

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class DataSyncReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        // Aquí podrías iniciar el servicio de sincronización o programar tareas
        val intentSync = Intent(context, DataSyncJobService::class.java)
        context?.startService(intentSync)
    }
}