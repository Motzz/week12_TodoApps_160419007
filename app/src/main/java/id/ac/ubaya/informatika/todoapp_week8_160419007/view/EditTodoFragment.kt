package id.ac.ubaya.informatika.todoapp_week8_160419007.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.ac.ubaya.informatika.todoapp_week8_160419007.R
import id.ac.ubaya.informatika.todoapp_week8_160419007.databinding.FragmentEditToDoBinding
import id.ac.ubaya.informatika.todoapp_week8_160419007.model.Todo
import id.ac.ubaya.informatika.todoapp_week8_160419007.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_create_to_do.*


class EditTodoFragment : Fragment(),RadioClickListener,SaveChangesListener {
    private  lateinit var  viewModel:DetailViewModel
    private lateinit var dataBinding:FragmentEditToDoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=DataBindingUtil.inflate<FragmentEditToDoBinding>(inflater,R.layout.fragment_edit_to_do, container, false)
        return dataBinding.root
        //return inflater.inflate(R.layout.fragment_create_to_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=ViewModelProvider(this).get(DetailViewModel::class.java)
        val uuid=EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        dataBinding.radiolistener=this//harus di ciptakan week 10
        dataBinding.listener=this



        /*txtJudulTodo.text="Edit Todo"
        btnCreate.text="Save Changes"*/

        /*btnCreate.setOnClickListener {
            val radio=view.findViewById<RadioButton>(radioGroupPriority.checkedRadioButtonId)
            viewModel.update(txtTitle.text.toString(),txtNotess.text.toString(),radio.tag.toString().toInt(),uuid)

            Toast.makeText(view.context,"Todo updated",Toast.LENGTH_SHORT).show()
        }*/

        observerViewModel()
    }

    fun observerViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo=it
            /*txtTitle.setText(it.title)
            txtNotess.setText(it.notes)

            if (it.priority==3)
            {
                radioHigh.isChecked=true
            }
            else if(it.priority== 2)
            {
                radioMedium.isChecked=true
            }
            else
            {
                radioLow.isChecked=true
            }*/
        })
    }

    override fun RadioClickListener(v: View, obj: Todo) {
        obj.priority=v.tag.toString().toInt()
    }

    override fun SaveChanges(v: View, obj: Todo) {
        //Log.d("cek obj",obj.toString()) //buat cek aja
        viewModel.update(obj.title,obj.notes,obj.priority,obj.uuid)
        Toast.makeText(v.context,"Todo updated",Toast.LENGTH_SHORT).show()
    }


}