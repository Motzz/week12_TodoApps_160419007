package id.ac.ubaya.informatika.todoapp_week8_160419007.view

import android.view.View
import android.widget.CompoundButton
import id.ac.ubaya.informatika.todoapp_week8_160419007.model.Todo

interface TodoCheckedChangeListener{
    fun TodoCheckedChange(cb:CompoundButton,isChecked:Boolean,obj:Todo)
}
interface TodoEditClickListener{
    fun TodoEditClick(v:View)
}
interface  RadioClickListener{
    fun RadioClickListener(v:View,obj: Todo)
}

interface SaveChangesListener{
    fun SaveChanges(v:View,obj: Todo)
}

//week 12
interface ButtonAddTodoClickListener{
    fun onButtonAddTodo(v:View)
}
interface DateClickListener{
    fun onDateClick(v:View)
}
interface TimeClickListener{
    fun onTimeClick(v:View)
}


