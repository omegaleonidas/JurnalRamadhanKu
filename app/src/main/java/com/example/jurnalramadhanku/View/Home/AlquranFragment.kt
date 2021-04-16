package com.example.jurnalramadhanku.View.Home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.jurnalramadhanku.Adapter.AdapterAlquran
import com.example.jurnalramadhanku.Local.model.DatabaseTarget
import com.example.jurnalramadhanku.Local.model.model.Alquran
import com.example.jurnalramadhanku.ViewModel.AlquranViewModel
import com.example.jurnalramadhanku.databinding.DialogFromAlquranBinding
import com.example.jurnalramadhanku.databinding.FragmentAlquranBinding
import kotlinx.android.synthetic.main.fragment_alquran.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AlquranFragment : Fragment() {
    private var alquranDatabase: DatabaseTarget? = null
    lateinit var alquranViewModel: AlquranViewModel
    private lateinit var binding2: DialogFromAlquranBinding


    private var dialogView: Dialog? = null
    private lateinit var binding: FragmentAlquranBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAlquranBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        alquranViewModel.showAlquranView()
        attachObserve()
        binding.floatingActionButtonAlquran.setOnClickListener {
     //       Toast.makeText(context, "button berhasil tertekan ", Toast.LENGTH_SHORT).show()
            getshowAddDialog()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        alquranViewModel = ViewModelProvider(this).get(
            AlquranViewModel
            ::class.java
        )
        alquranDatabase = context?.let { DatabaseTarget.getInstance(it) }
    }

    private fun getshowAddDialog() {

        val dialog = AlertDialog.Builder(context)
         binding2 = DialogFromAlquranBinding.inflate(layoutInflater)

     //   val view: View = layoutInflater.inflate(R.layout.dialog_from_alquran, null)
        dialog.setView(binding2.root)
        binding2.btnSaveAlquran.setOnClickListener {

            val tilawah = binding2.dialogTilawah.text.toString()
            val menghafal = binding2.dialogMenghafal.text.toString()
            val murajaah = binding2.dialogMurajaah.text.toString()


            val tanggal = binding2.tvTimeAlquran.text.toString()


            alquranViewModel.addAlquranView(Alquran(null, tanggal, tilawah, menghafal, murajaah))


            //   dialogView?.dismiss()


        }
        binding2.imageViewalquran.setOnClickListener {
            dialogView?.dismiss()
        }
        dialogView = dialog.create()
        dialogView?.show()

    }


    private fun getDate(): String {
        val date: Date = Calendar.getInstance().time
        val formatter: DateFormat = SimpleDateFormat.getDateInstance()
        val formatDate: String = formatter.format(date)

        return formatDate

    }

    private fun showAlquran(it: List<Alquran>?) {
        recyclerViewAlquran.adapter = AdapterAlquran(it, object : AdapterAlquran.OnClickListener {
            override fun delete(item: Alquran?) {
                AlertDialog.Builder(context).apply {
                    setTitle("hapus")
                    setMessage("yakin hapus List Al-qur'an ?")
                    setCancelable(false)
                    setPositiveButton("yakin ") { dialogInterface, i ->
                        alquranViewModel.deleteAlquranView(item!!)
                    }
                    setNegativeButton("batal") { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }.show()
            }

            override fun update(item: Alquran?) {
                getShowUpdateData(item)


            }

        })
    }


    private fun getShowUpdateData(item: Alquran?) {

        val dialog = AlertDialog.Builder(context)
        binding2 = DialogFromAlquranBinding.inflate(layoutInflater)

        //   val view: View = layoutInflater.inflate(R.layout.dialog_from_alquran, null)
        dialog.setView(binding2.root)
        binding2.btnSaveAlquran.text = "update"



        binding2.tvTimeAlquran.setText(item?.tanggal)
            binding2.dialogTilawah.setText(item?.tilawah)
        binding2.dialogMenghafal.setText(item?.menghafal)
        binding2.dialogMurajaah.setText(item?.murajaan)




        binding2.btnSaveAlquran.setOnClickListener {
            val id = item?.id
            val tilawah = binding2.dialogTilawah.text.toString()
            val menghafal = binding2.dialogMenghafal.text.toString()
            val murajaah = binding2.dialogMurajaah.text.toString()


            val tanggal = binding2.tvTimeAlquran.text.toString()
            alquranViewModel.updateAlquranView(Alquran(id, tanggal, tilawah, menghafal, murajaah))

        }
        binding2.imageViewalquran.setOnClickListener {
            dialogView?.dismiss()
        }
        dialogView = dialog.create()
        dialogView?.show()

    }


    private fun attachObserve() {
        alquranViewModel.ShowAlquran.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showAlquran(it) })
        alquranViewModel.isError.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showError(it) })
        alquranViewModel.AddAlquran.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showAddJadwal(it) })
        alquranViewModel.UpdateAlquran.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showUpdateJadwal(it) })
        alquranViewModel.DeleteAlquran.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showDeleteJadwal(it) })
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showAddJadwal(

        it: Unit?
    ) {
        dialogView?.dismiss()
        Toast.makeText(context, "  berhasil disimpan", Toast.LENGTH_SHORT).show()
        alquranViewModel.showAlquranView()
    }

    private fun showUpdateJadwal(it: Unit?) {
        dialogView?.dismiss()
        Toast.makeText(context, " berhasil diupdate", Toast.LENGTH_SHORT).show()
        alquranViewModel.showAlquranView()
    }

    private fun showDeleteJadwal(it: Unit?) {
        Toast.makeText(context, "  berhasil dihapus", Toast.LENGTH_SHORT).show()
        alquranViewModel.showAlquranView()
    }


}