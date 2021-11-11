package id.ac.ubaya.informatika.todoapp_week8_160419007.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import id.ac.ubaya.informatika.todoapp_week8_160419007.R
import id.ac.ubaya.informatika.todoapp_week8_160419007.model.Todo
import id.ac.ubaya.informatika.todoapp_week8_160419007.util.NotificationHelper
import id.ac.ubaya.informatika.todoapp_week8_160419007.util.TofoWalker
import id.ac.ubaya.informatika.todoapp_week8_160419007.viewmodel.DetailViewModel
import id.ac.ubaya.informatika.todoapp_week8_160419007.viewmodel.ListTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_to_do.*
import java.util.concurrent.TimeUnit


class CreateToDoFragment : Fragment() {
    private lateinit var  viewModel:DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)

        btnCreate.setOnClickListener {
            //notif pemanggilan week 12
            val myWorkRequest= OneTimeWorkRequestBuilder<TofoWalker>()
                .setInitialDelay(10,TimeUnit.SECONDS)
                .setInputData(workDataOf(
                    "title" to "todo created",
                    "message" to "A new created todo has been created"))
                .build()
            WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)

            val radio= view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)//manggil id radiobutton yang terpilih
            var todo = Todo(txtTitle.text.toString(),txtNotess.text.toString(),radio.tag.toString().toInt(),0)
            viewModel.addTodo(todo)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            //notif pemanggilan week 12
            NotificationHelper(view.context)
                .createNotification("Todo Created",
                    "A new todo has been created! Stay focus!")

            Navigation.findNavController(it).popBackStack()//untuk destroy fragment
        }
    }

}