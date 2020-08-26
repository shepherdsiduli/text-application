package com.shepherd.text.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.shepherd.text.R
import com.shepherd.text.framework.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment(), ListAction {

    private val textsListAdapter = TextsListAdapter(arrayListOf(), this)
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textsListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = textsListAdapter
        }
        addText.setOnClickListener { goToTextDetails() }

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.texts.observe(this, Observer { textsList ->
            loadingView.visibility = View.GONE
            textsListView.visibility = View.VISIBLE
            textsListAdapter.updateTexts(textsList.sortedByDescending { it.updateTime })
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getTexts()
    }

    private fun goToTextDetails(id: Long = 0L) {
        var action = ListFragmentDirections.actionGoToText(id)
        Navigation.findNavController(textsListView).navigate(action)
    }

    override fun onClick(id: Long) {
        goToTextDetails(id)
    }
}
