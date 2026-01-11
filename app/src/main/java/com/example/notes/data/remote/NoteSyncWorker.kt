package com.example.notes.data.remote

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.notes.domain.NoteRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class NoteSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val repository: NoteRepository
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {

        return try {
            Log.d("NoteSyncWorker", "Worker started")

            repository.sync()
            Log.d("NoteSyncWorker", "Worker finished")
            Result.success()
        } catch (e: Exception) {
            Log.e("NoteSyncWorker", "Worker failed", e)
            Result.retry()
        }
    }
}
