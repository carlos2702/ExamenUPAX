package com.example.examenupax.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examenupax.R

class MyAppAdapter (val context: Context, myappModelArrayList: ArrayList<Employee>): RecyclerView.Adapter<MyAppAdapter.MyViewHolder>() {

     var inflater: LayoutInflater? = null
    private var myappModelArrayList: ArrayList<Employee>? = null

    init {
        this.myappModelArrayList = myappModelArrayList
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.id.setText(myappModelArrayList!![position].id)
        holder.mail.setText(myappModelArrayList!![position].mail)
        holder.name.setText(myappModelArrayList!![position].name)
    }

    override fun getItemCount(): Int {
        return if(myappModelArrayList!!.size >= 0){
            myappModelArrayList!!.size
        }else{
            0
        }

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView
        var mail: TextView
        var name: TextView

        init {
            id = itemView.findViewById(R.id.idd)
            mail = itemView.findViewById(R.id.correod)
            name = itemView.findViewById(R.id.named)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAppAdapter.MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }
}

