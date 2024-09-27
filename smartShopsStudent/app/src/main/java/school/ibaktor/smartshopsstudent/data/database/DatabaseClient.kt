package school.ibaktor.smartshopsstudent.data.database

import android.content.Context
import androidx.room.Room

object DatabaseClient {
    @Volatile
    private var instance: AppDatabase? = null

    fun getDatabase( context : Context ): AppDatabase{
        return instance ?: synchronized(this){
            val newInstance = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "smartshop"
            ).addMigrations(MIGRATION_1_TO_2)
                .build()
            instance = newInstance
            newInstance
        }

    }
}