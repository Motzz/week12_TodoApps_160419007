package id.ac.ubaya.informatika.todoapp_week8_160419007.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import id.ac.ubaya.informatika.todoapp_week8_160419007.R
import id.ac.ubaya.informatika.todoapp_week8_160419007.databinding.TodoItemLayoutBinding
import id.ac.ubaya.informatika.todoapp_week8_160419007.model.Todo
import id.ac.ubaya.informatika.todoapp_week8_160419007.viewmodel.ListTodoViewModel
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>,val adapterOnClick:(Any)-> Unit) : RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>(),TodoCheckedChangeListener,TodoEditClickListener {
    class TodoListViewHolder (var view: TodoItemLayoutBinding):RecyclerView.ViewHolder(view.root)
   // private lateinit var  viewModel: ListTodoViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout, parent, false) // gabisa pake ini lagi
        val view = DataBindingUtil.inflate<TodoItemLayoutBinding>(inflater,R.layout.todo_item_layout, parent, false)
        return TodoListViewHolder(view)

    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {


        holder.view.todo=todoList[position]
        holder.view.listener= this //harus diciptakan begini dulu agar bisa dipakai
        holder.view.editListener=this //harus diciptakan begini dulu agar bisa dipakai
            /* checkTask.text=todoList[position].title +" "+todoList[position].priority
             imageEdit.setOnClickListener {
                 val action= ToDoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
                 Navigation.findNavController(it).navigate(action)
             }

             checkTask.setOnCheckedChangeListener { compoundButton/*checkbox*/, isChecked ->
                 if (isChecked)
                 {
                     adapterOnClick(todoList[position].uuid)
                     //viewModel.isDone(todoList[position].uuid)
                 }
             }*/
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun TodoCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if (isChecked)
        {
            adapterOnClick(obj)
            //viewModel.isDone(todoList[position].uuid)
        }
    }

    override fun TodoEditClick(v: View) { //v merumakan image view
        val action= ToDoListFragmentDirections.actionEditTodoFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

}