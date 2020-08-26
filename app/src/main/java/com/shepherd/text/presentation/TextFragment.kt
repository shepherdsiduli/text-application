package com.shepherd.text.presentation

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.shepherd.core.data.Text
import com.shepherd.text.R
import com.shepherd.text.framework.TextViewModel
import kotlinx.android.synthetic.main.fragment_text.*

class TextFragment : Fragment() {

    private var textId = 0L

    private lateinit var viewModel: TextViewModel

    private var currentText = Text( "", 0L, 0L)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_text, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TextViewModel::class.java)

        arguments?.let {
            textId = TextFragmentArgs.fromBundle(it).textId
        }

        if (textId != 0L){
            viewModel.getText(textId)
        }

        checkButton.setOnClickListener {
            if(contentView.text.toString() != ""){
                val time = System.currentTimeMillis()
                currentText.content = contentView.text.toString()
                currentText.updateTime = time
                if(currentText.id == 0L){
                    currentText.creationTime = time
                }
                viewModel.saveText(currentText)
                hideKeyboard()
                Navigation.findNavController(it).popBackStack()
            } else{
                Navigation.findNavController(it).popBackStack()
            }
        }

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.saved.observe(this, Observer {
            if(it){
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT).show()
                hideKeyboard()
            } else {
                Toast.makeText(context, "Something went wrong, please try again", Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.currentText.observe(this, Observer { text ->
            text?.let {
                currentText = it
                contentView.setText(it.content, TextView.BufferType.EDITABLE)
            }
        })
    }

    private fun hideKeyboard(){
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(contentView.windowToken, 0)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.text_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.deleteText -> {
                if(context != null && textId != 0L){
                    AlertDialog.Builder(context!!)
                        .setTitle("Delete text")
                        .setMessage("Are you sure you want to delete this?")
                        .setPositiveButton("Yes"){ dialogInterface, i ->
                            viewModel.deleteText(currentText)
                        }
                        .setNegativeButton("No"){ dialogInterface, i ->  }
                        .create()
                        .show()
                }
            }
        }

        return true
    }
}