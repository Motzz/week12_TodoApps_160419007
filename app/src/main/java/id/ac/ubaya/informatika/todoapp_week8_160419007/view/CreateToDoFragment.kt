package id.ac.ubaya.informatika.todoapp_week8_160419007.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.text.format.DateFormat//date harus gini
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.RadioButton
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import id.ac.ubaya.informatika.todoapp_week8_160419007.R
import id.ac.ubaya.informatika.todoapp_week8_160419007.databinding.FragmentCreateToDoBinding
import id.ac.ubaya.informatika.todoapp_week8_160419007.model.Todo
import id.ac.ubaya.informatika.todoapp_week8_160419007.util.NotificationHelper
import id.ac.ubaya.informatika.todoapp_week8_160419007.util.TofoWalker
import id.ac.ubaya.informatika.todoapp_week8_160419007.viewmodel.DetailViewModel
import id.ac.ubaya.informatika.todoapp_week8_160419007.viewmodel.ListTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_to_do.*
import kotlinx.android.synthetic.main.fragment_create_to_do.view.*
import java.util.*
import java.util.concurrent.TimeUnit


class CreateToDoFragment : Fragment(),ButtonAddTodoClickListener ,RadioClickListener,
    DateClickListener,TimeClickListener,
    DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{
    private lateinit var  viewModel:DetailViewModel
    private lateinit var  databinding:FragmentCreateToDoBinding

    var year=0
    var moth=0
    var day=0
    var hour=0
    var minute=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        databinding= DataBindingUtil.inflate(inflater,R.layout.fragment_create_to_do,container,false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        databinding.todo=Todo("","",3,0,0)
        databinding.listener=this
        databinding.radiolistener=this
        databinding.listenerDate=this
        databinding.listenerTime=this

       /* btnCreate.setOnClickListener {
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
        }*/
    }

    override fun onButtonAddTodo(v: View) {
        val c = Calendar.getInstance()
        c.set(year,moth,day,hour,minute,0)
        val today=Calendar.getInstance()
        val dif=(c.timeInMillis/1000)-(today.timeInMillis/1000L)
        databinding.todo!!.todo_date=(c.timeInMillis/1000L).toInt()



        viewModel.addTodo(databinding.todo!!)
        Toast.makeText(v.context, "Data added", Toast.LENGTH_LONG).show()

        val myWorkRequest= OneTimeWorkRequestBuilder<TofoWalker>()
            .setInitialDelay(dif,TimeUnit.SECONDS)
            .setInputData(workDataOf(
                "title" to "todo created",
                "message" to "A new created todo has been created"))
            .build()
        WorkManager.getInstance(requireContext()).enqueue(myWorkRequest)
        Navigation.findNavController(v).popBackStack()//untuk destroy fragment
    }

    override fun RadioClickListener(v: View, obj: Todo) {
        obj.priority=v.tag.toString().toInt()
    }

    override fun onDateClick(v: View) {
        val c = Calendar.getInstance()
        val year=c.get(Calendar.YEAR)
        val month=c.get(Calendar.MONTH)
        val day=c.get(Calendar.DAY_OF_MONTH)
        activity?.let {
            it->DatePickerDialog(it,this,year,month,day).show()
        }
    }

    override fun onTimeClick(v: View) {
        val c = Calendar.getInstance()
        val hour=c.get(Calendar.HOUR_OF_DAY)
        val MINUTE=c.get(Calendar.MINUTE)
        TimePickerDialog(activity,this,hour,minute,DateFormat.is24HourFormat(activity)).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Calendar.getInstance().let {
            it.set(year,month,day)
            databinding.root.txtDate.setText(day.toString().padStart(2,'0')+"-"+
                    (month+1).toString().padStart(2,'0')+"-"+
                                             year.toString().padStart(2,'0'))
            this.year=year
            this.moth=moth
            this.day=day
        }
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        databinding.root.txtTime.setText(hourOfDay.toString().padStart(2,'0')+":"+
                minute.toString().padStart(2,'0'))

        hour=hourOfDay
        this.minute=minute
    }

}