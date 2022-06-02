package com.example.mynewsapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mynewsapp.R
import com.example.mynewsapp.entity.SuggestedTopics
import com.example.mynewsapp.view.DetailTopicsActivity


class SuggestedTopicsRecyclerViewAdapter()  : RecyclerView.Adapter<SuggestedTopicsRecyclerViewAdapter.SuggestedTopicsViewHolder>(){

    val suggestedTopics = mutableListOf<SuggestedTopics>()

    fun setData(data: List<SuggestedTopics>){
        suggestedTopics.clear()
        suggestedTopics.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestedTopicsViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_category_news, parent, false)
        return SuggestedTopicsViewHolder(view)

    }

    override fun getItemCount(): Int {
        return suggestedTopics.size

    }

    override fun onBindViewHolder(holder: SuggestedTopicsViewHolder, position: Int) {

        holder.bind(suggestedTopics[position])
    }

    class SuggestedTopicsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.findViewById<ImageView>(R.id.image)
        val text = itemView.findViewById<TextView>(R.id.text)

        fun bind(data: SuggestedTopics){
            with(itemView){
                text.text = data.title
                image.setImageResource(data.image)

                image.setOnClickListener {
                    showDetailNews(context, data)
                }
            }
        }

        private fun showDetailNews(context: Context, data: SuggestedTopics) {
            val intent = Intent(context, DetailTopicsActivity::class.java)
            intent.putExtra(DetailTopicsActivity.DETAIL_KEY, data)
            context.startActivity(intent)
        }

    }
}
