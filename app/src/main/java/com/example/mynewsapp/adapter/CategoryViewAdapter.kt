package com.example.mycafeapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycafeapp.R
import com.example.mycafeapp.entity.Category
import com.example.mycafeapp.view.CategoryListActivity


class CategoryViewAdapter()  : RecyclerView.Adapter<CategoryViewAdapter.CategoryViewHolder>(){

    val categories = mutableListOf<Category>()

    fun setData(data: List<Category>){
        categories.clear()
        categories.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_category_cafe, parent, false)
        return CategoryViewHolder(view)

    }

    override fun getItemCount(): Int {
        return categories.size

    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.bind(categories[position])
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val image = itemView.findViewById<ImageView>(R.id.image)
        val text = itemView.findViewById<TextView>(R.id.text)

        fun bind(data: Category){
            with(itemView){
                text.text = data.name
                image.setImageResource(data.image)

                image.setOnClickListener {
                    showDetailCafe(context, data)
                }
            }
        }

        private fun showDetailCafe(context: Context, data: Category) {
            val intent = Intent(context, CategoryListActivity::class.java)
            intent.putExtra(CategoryListActivity.DETAIL_KEY, data.name)
            context.startActivity(intent)
        }

    }
}
