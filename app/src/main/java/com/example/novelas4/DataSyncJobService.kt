package com.example.novelas4

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.AsyncTask
import android.widget.Toast

class DataSyncJobService : JobService() {

    override fun onStartJob(params: JobParameters?): Boolean {
        // Ejecutar la tarea de sincronización en segundo plano
        DataSyncTask(this).execute(params)
        return true // El trabajo continúa ejecutándose en segundo plano
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false // No es necesario reintentar si se interrumpe el trabajo
    }

    private class DataSyncTask(val jobService: JobService) : AsyncTask<JobParameters, Void, JobParameters>() {
        override fun doInBackground(vararg params: JobParameters?): JobParameters? {
            // Aquí se realizaría la sincronización con Firebase o cualquier otro servidor
            Thread.sleep(3000) // Simulación de sincronización
            return params[0]
        }

        override fun onPostExecute(result: JobParameters?) {
            Toast.makeText(jobService, "Sincronización completada", Toast.LENGTH_SHORT).show()
            jobService.jobFinished(result, false)
        }
    }
}