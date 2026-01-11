package com.example.notes.data.remote

import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.notes.domain.remote.SyncScheduler
import javax.inject.Inject

class WorkManagerSyncScheduler @Inject constructor(
    private val workManager: WorkManager
) : SyncScheduler {

    override fun scheduleSync() {
        val request = OneTimeWorkRequestBuilder<NoteSyncWorker>()
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .build()

        workManager.enqueueUniqueWork(
            "note_sync",
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}
