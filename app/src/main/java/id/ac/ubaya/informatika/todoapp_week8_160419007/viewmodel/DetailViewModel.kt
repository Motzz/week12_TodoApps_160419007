package id.ac.ubaya.informatika.todoapp_week8_160419007.viewmodel

import android.app.Application
import android.content.pm.LauncherActivityInfo
import android.icu.text.CaseMap
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.informatika.todoapp_week8_160419007.model.Todo
import id.ac.ubaya.informatika.todoapp_week8_160419007.model.TodoDatabase
import id.ac.ubaya.informatika.todoapp_week8_160419007.util.buildDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class DetailViewModel(application: Application):AndroidViewModel(application),CoroutineScope {
    private val job = Job()
    val todoLD=MutableLiveData<Todo>()

    //week9
    fun fetch(uuid: Int)
    {
        launch{
            val db = buildDB(getApplication())//pemanggilan database
            todoLD.value=db.todoDao().selectTodo(uuid)
        }

    }

    fun update(title: String,notes:String,priority:Int,uuid: Int)
    {
        launch {
            val db= buildDB(getApplication())
            db.todoDao().update(title,notes,priority,uuid)
        }
    }


    //week8
    fun addTodo(todo:Todo) {
        launch {
            val db = buildDB(getApplication())//pemanggilan database
            db.todoDao().insertAll(todo)
        }
    }



    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}