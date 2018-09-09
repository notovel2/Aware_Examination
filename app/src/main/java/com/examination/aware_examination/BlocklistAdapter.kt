package com.examination.aware_examination

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.blocklist_item.view.*

class BlocklistAdapter(val items : ArrayList<String>, val context: Context) : RecyclerView.Adapter<BlocklistAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.phoneNo?.text = items[position]
        holder.delButton.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Confirmation")
            builder.setMessage("Delete ${items[position]} ?")

            builder.setPositiveButton("YES"){dialog, which ->
                items.removeAt(position)
                Log.d("Position : ",position.toString())
                Log.d("Data : ",items.toString())
//            this.notifyItemRemoved(position)
                this.notifyDataSetChanged()
            }
            builder.setNeutralButton("Cancel"){_,_ ->
            }

            val dialog: AlertDialog = builder.create()

            // Display the alert dialog on app interface
            dialog.show()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val textView = LayoutInflater.from(parent.context)
                .inflate(R.layout.blocklist_item, parent, false)
        return ViewHolder(textView)

//        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.blocklist_item, parent, false))
    }


    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val phoneNo = itemView.phoneNoTextView
        val delButton = itemView.delBtn
    }

}


