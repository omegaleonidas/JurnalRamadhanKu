package com.example.jurnalramadhanku.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jurnalramadhanku.Local.model.model.Target
import com.example.jurnalramadhanku.R
import kotlinx.android.synthetic.main.item_target.view.*

class AdapterTarget(
    private val data: List<Target>?,
    val itemClick: OnClickListener
) :
    RecyclerView.Adapter<AdapterTarget.ViewHolder>() {


    class ViewHolder(
        val view: View,
        val itemClick: OnClickListener

    ) :
        RecyclerView.ViewHolder(view) {
        fun bind(item: Target?) {
            view.tvTarget.text = item?.target

            view.imageButton.setOnClickListener {
                itemClick.update(item)

            }
            view.imageButton2.setOnClickListener {
                itemClick.delete(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_target, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = data?.size ?: 0


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Target? = data?.get(position)
        holder.bind(item)
    }

    interface OnClickListener {
        fun update(item: Target?)
        fun delete(item: Target?)
    }


}