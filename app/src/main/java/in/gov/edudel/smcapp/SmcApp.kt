package `in`.gov.edudel.smcapp

import android.app.Application
import androidx.room.Room
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class SmcApp:Application() {
    override fun onCreate() {
        super.onCreate()
        requestQueue = Volley.newRequestQueue(this)
        db = Room.databaseBuilder(this, AppDatabase::class.java, "smc-db")
            .build()

    }

    companion object {
        lateinit var requestQueue: RequestQueue
        lateinit var db: AppDatabase
    }
}