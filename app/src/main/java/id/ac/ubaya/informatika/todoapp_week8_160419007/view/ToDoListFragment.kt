package id.ac.ubaya.informatika.todoapp_week8_160419007.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ubaya.informatika.todoapp_week8_160419007.R
import id.ac.ubaya.informatika.todoapp_week8_160419007.model.Todo
import id.ac.ubaya.informatika.todoapp_week8_160419007.viewmodel.ListTodoViewModel
import kotlinx.android.synthetic.main.fragment_to_do_list.*


class ToDoListFragment : Fragment() {
    private lateinit var  viewModel:ListTodoViewModel
    private var todoListAdapter:TodoListAdapter=TodoListAdapter(arrayListOf(),{ item -> doClick(item)})

    fun doClick(item:Any)
    {
        viewModel.clearTask(item as Todo)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel=ViewModelProvider(this).get(ListTodoViewModel::class.java)
        viewModel.refresh()

        recTodoList.layoutManager=LinearLayoutManager(context)
        recTodoList.adapter=todoListAdapter

        fabAdd.setOnClickListener{
            val action= ToDoListFragmentDirections.actionCreateTodo()
            Navigation.findNavController(it).navigate(action)
        }
        observeViewModel()

    }

    fun observeViewModel()
    {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            todoListAdapter.updateTodoList(it)
            if(it.isEmpty())
            {
                txtEmpty.visibility = View.VISIBLE
            }
            else
            {
                txtEmpty.visibility = View.GONE
            }


        })

    }



}