package id.ac.ubaya.informatika.todoapp_week8_160419007.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.room.Room
import id.ac.ubaya.informatika.todoapp_week8_160419007.model.Todo
import id.ac.ubaya.informatika.todoapp_week8_160419007.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application):AndroidViewModel(application),CoroutineScope {


    fun addTodo(todo:Todo) {
        launch {
            val db = Room.databaseBuilder(
                    getApplication(), TodoDatabase::class.java,
                    "newtododb").build()
            db.todoDao().insertAll(todo)
        }
    }


    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}