package com.example.jurnalramadhanku.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jurnalramadhanku.Local.model.model.Alquran
import com.example.jurnalramadhanku.databinding.ItemAlquranBinding

class AdapterAlquran(
    private val data: List<Alquran>?,
    val itemClick: OnClickListener
) :
    RecyclerView.Adapter<AdapterAlquran.ViewHolder>() {


    inner class ViewHolder(
        val binding: ItemAlquranBinding

    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Alquran?) {
            binding.tvTanggalAlquran.text = item?.tanggal
            binding.tvMenghafal.text = item?.menghafal
            binding.tvMurajaah.text = item?.murajaan
            binding.tvTilawah.text = item?.tilawah


            binding.imageButtonKajian3.setOnClickListener {
                itemClick.update(item)

            }
            binding.imageButtonKajian2.setOnClickListener {
                itemClick.delete(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
                ItemAlquranBinding.inflate(
                    LayoutInflater.from(parent.context),parent,false)



    )


    override fun getItemCount(): Int = data?.size ?: 0


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Alquran? = data?.get(position)
        holder.bind(item)
    }

    interface OnClickListener {
        fun update(item: Alquran?)
        fun delete(item: Alquran?)
    }

}

