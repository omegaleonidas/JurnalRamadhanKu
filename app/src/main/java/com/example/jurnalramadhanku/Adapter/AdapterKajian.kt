package com.example.jurnalramadhanku.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jurnalramadhanku.Local.model.model.Kajian
import com.example.jurnalramadhanku.Local.model.model.Target
import com.example.jurnalramadhanku.R
import kotlinx.android.synthetic.main.dialog_from_kajian.view.*
import kotlinx.android.synthetic.main.item_kajian.view.*
import kotlinx.android.synthetic.main.item_target.view.*

class AdapterKajian(
    private val data: List<Kajian>?,
    val itemClick: OnClickListener
) :
    RecyclerView.Adapter<AdapterKajian.ViewHolder>() {


    class ViewHolder(
        val view: View,
        val itemClick: OnClickListener

    ) :
        RecyclerView.ViewHolder(view) {
        fun bind(item: Kajian?) {
            view.tvTanggalKajian.text = item?.time
            view.tvJudulKajian.text = item?.judul
            view.tvPemateri.text = item?.pemateri
            view.tvLocation.text = item?.lokasi
            view.tvCatatan.text = item?.quote

            view.imageButtonKajian3.setOnClickListener {
                itemClick.update(item)

            }
            view.imageButtonKajian2.setOnClickListener {
                itemClick.delete(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_kajian, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = data?.size ?: 0


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Kajian? = data?.get(position)
        holder.bind(item)
    }

    interface OnClickListener {
        fun update(item: Kajian?)
        fun delete(item: Kajian?)
    }


}
