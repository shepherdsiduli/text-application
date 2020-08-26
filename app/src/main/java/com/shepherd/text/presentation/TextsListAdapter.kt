package com.shepherd.text.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shepherd.core.data.Text
import com.shepherd.text.R
import kotlinx.android.synthetic.main.item_text.view.*
import java.text.SimpleDateFormat
import java.util.*

class TextsListAdapter (var texts: ArrayList<Text>, val actions: ListAction): RecyclerView.Adapter<TextsListAdapter.TextViewHolder>(){

    fun updateTexts(newTexts: List<Text>){
        texts.clear()
        texts.addAll(newTexts)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder = TextViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_text, parent, false)
    )

    override fun getItemCount() = texts.size

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.bind(texts[position])
    }

    inner class TextViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val layout = view.textLayout
        private val textContent = view.content
        private val textDate = view.date
        fun bind(text: Text){
            textContent.text = text.content
            val sdf = SimpleDateFormat("MM dd, HH:mm:ss")
            val resultDate = Date(text.updateTime)
            textDate.text = "Last updated: ${sdf.format(resultDate)}"

            layout.setOnClickListener{actions.onClick(text.id)}
        }
    }
}