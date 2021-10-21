package id.ac.ubaya.informatika.todoapp_week8_160419007.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ubaya.informatika.todoapp_week8_160419007.util.MIGRATION_1_2

@Database(entities = arrayOf(Todo::class),version = 2)
abstract class TodoDatabase:RoomDatabase() {
    abstract fun todoDao():TodoDao

    companion object {
        @Volatile private var instance: TodoDatabase ?= null //voletile agar instance bisa diket oleh thread lain
        private val LOCK = Any()

        private fun buildDatabase(context: Context) =Room.databaseBuilder(//3 parameter
                        context.applicationContext,
                        TodoDatabase::class.java,
                        "newtododb"
        )
            .addMigrations(MIGRATION_1_2)
            .build()

        operator fun invoke(context:Context) {//fungsi yang akan terpanggil jika ada sesuatu yang akan implementasi tododatabase
            if(instance!=null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }

    }


}