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
import id.ac.ubaya.informatika.todoapp_week8_160419007.R
import id.ac.ubaya.informatika.todoapp_week8_160419007.model.Todo
import id.ac.ubaya.informatika.todoapp_week8_160419007.viewmodel.DetailViewModel
import id.ac.ubaya.informatika.todoapp_week8_160419007.viewmodel.ListTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_to_do.*


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
            val radio= view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)//manggil id radiobutton yang terpilih
            var todo = Todo(txtTitle.text.toString(),txtNotess.text.toString(),radio.tag.toString().toInt(),0)
            viewModel.addTodo(todo)
            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()//untuk destroy fragment
        }
    }

}