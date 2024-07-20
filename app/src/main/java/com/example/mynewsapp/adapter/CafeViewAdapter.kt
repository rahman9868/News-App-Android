package com.example.mycafeapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.mycafeapp.R
import com.example.mycafeapp.entity.Cafe
import com.example.mycafeapp.view.DetailCafeActivity

class CafeViewAdapter() : RecyclerView.Adapter<CafeViewAdapter.CafeViewHolder>(){

    val mData = mutableListOf<Cafe>()

    fun setData(data : List<Cafe>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CafeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_cafe_list, parent, false)
        return CafeViewHolder(view)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: CafeViewHolder, position: Int) {

        holder.bind(mData[position])
    }

    class CafeViewHolder(itemView: View) : ViewHolder(itemView) {

        val name: TextView = itemView.findViewById(R.id.txtName)
        val time : TextView = itemView.findViewById(R.id.txtTime)
        val location : TextView = itemView.findViewById(R.id.txtLocation)
        val rate : TextView = itemView.findViewById(R.id.txtRate)
        val reviews : TextView = itemView.findViewById(R.id.txtReviews)
        val pictCafe : ImageView = itemView.findViewById(R.id.ivPictCafe)
        val item : View = itemView

        fun bind(data: Cafe){
            val block: (View).() -> Unit = {


                pictCafe.setImageResource(data.picture)
                name.text = data.name + " - " + data.location
                location.text = data.address
                time.text = data.openTime +" - "+ data.closeTime
                rate.text = data.rate.toString()
                reviews.text = data.countRate.toString()

                this.setOnClickListener {
                    showDetailCafe(context, data)
                }
            }
            with(itemView, block)

        }

        private fun showDetailCafe(context: Context,data: Cafe) {
            val intent = Intent(context, DetailCafeActivity::class.java)
            intent.putExtra(DetailCafeActivity.DETAIL_KEY,data.id)
            context.startActivity(intent)
        }

    }

}

