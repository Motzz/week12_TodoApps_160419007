package id.ac.ubaya.informatika.todoapp_week8_160419007.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import id.ac.ubaya.informatika.todoapp_week8_160419007.model.Todo
import id.ac.ubaya.informatika.todoapp_week8_160419007.model.TodoDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application):AndroidViewModel(application),CoroutineScope {

    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()


    fun refresh() { //method untuk select all dari kueri todoDao
        loadingLD.value = true
        todoLoadErrorLD.value = false
        launch {
            val db = Room.databaseBuilder(getApplication(),TodoDatabase::class.java, "newtododb").build()
            todoLD.value = db.todoDao().selectAllTodo()
        }
    }

    fun clearTask(todo: Todo) { //method kueri untuk delete
        launch {
            val db = Room.databaseBuilder(getApplication(),TodoDatabase::class.java, "newtododb").build()
            db.todoDao().deleteTodo(todo)//delete todo

            todoLD.value = db.todoDao().selectAllTodo()//dan select all lagi
        }
    }




    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main


}