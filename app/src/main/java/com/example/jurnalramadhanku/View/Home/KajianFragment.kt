package com.example.jurnalramadhanku.View.Home

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jurnalramadhanku.Adapter.AdapterKajian
import com.example.jurnalramadhanku.Local.model.DatabaseTarget
import com.example.jurnalramadhanku.Local.model.model.Kajian
import com.example.jurnalramadhanku.ViewModel.KajianViewModel
import com.example.jurnalramadhanku.databinding.*
import kotlinx.android.synthetic.main.fragment_kajian.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class KajianFragment : Fragment() {

    private var kajianDatabase: DatabaseTarget? = null
    lateinit var kajianViewModel: KajianViewModel
    private lateinit var binding: FragmentKajianBinding
    private lateinit var binding2: DialogFromKajianBinding


    private var dialogView: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentKajianBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        kajianViewModel.showKajianView()
        attachObserve()


        binding.floatingActionButton1.setOnClickListener {
         //   Toast.makeText(context, "button berhasil tertekan ", Toast.LENGTH_SHORT).show()
            getshowAddDialog()

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        kajianViewModel = ViewModelProvider(this).get(
            KajianViewModel
            ::class.java
        )
        kajianDatabase = context?.let { DatabaseTarget.getInstance(it) }
    }

    private fun getshowAddDialog() {

        val dialog = AlertDialog.Builder(context)
        binding2 = DialogFromKajianBinding.inflate(layoutInflater)

        //   val view: View = layoutInflater.inflate(R.layout.dialog_from_alquran, null)
        dialog.setView(binding2.root)

        binding2.btnSaveKajian.setOnClickListener {


            val judul = binding2.dialogJudul.text.toString()
            val pemateri = binding2.dialogPemateri.text.toString()
            val alamat = binding2.dialogAlamat.text.toString()
            val catatan = binding2.dialogCatatan.text.toString()

            val tanggal = binding2.tvTime.text.toString()


            kajianViewModel.addKajianView(Kajian(null, tanggal, judul, pemateri, alamat, catatan))


            //   dialogView?.dismiss()


        }
        binding2.imageView1.setOnClickListener {
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

    private fun showKajian(it: List<Kajian>?) {
        recyclerViewKajian.adapter = AdapterKajian(it, object : AdapterKajian.OnClickListener {
            override fun delete(item: Kajian?) {
                AlertDialog.Builder(context).apply {
                    setTitle("hapus")
                    setMessage("yakin hapus Kajian ?")
                    setCancelable(false)
                    setPositiveButton("yakin ") { dialogInterface, i ->
                        kajianViewModel.deleteKajianView(item!!)
                    }
                    setNegativeButton("batal") { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }.show()
            }

            override fun update(item: Kajian?) {
                getShowUpdateData(item)


            }

        })
    }


    private fun getShowUpdateData(item: Kajian?) {

        val dialog = AlertDialog.Builder(context)
        binding2 = DialogFromKajianBinding.inflate(layoutInflater)

        //   val view: View = layoutInflater.inflate(R.layout.dialog_from_alquran, null)
        dialog.setView(binding2.root)

        binding2.btnSaveKajian.text = "update"
        binding2.dialogJudul.setText(item?.judul)
        binding2.dialogPemateri.setText(item?.pemateri)
        binding2.dialogAlamat.setText(item?.lokasi)
        binding2.dialogCatatan.setText(item?.quote)
        binding2.tvTime.setText(item?.time)



        binding2.btnSaveKajian.setOnClickListener {
            val id = item?.id
            val judul = binding2.dialogJudul.text.toString()
            val pemateri = binding2.dialogPemateri.text.toString()
            val alamat = binding2.dialogAlamat.text.toString()
            val catatan = binding2.dialogCatatan.text.toString()

            val tanggal = binding2.tvTime.text.toString()
            kajianViewModel.updateKajianView(Kajian(id, tanggal, judul, pemateri, alamat, catatan))

        }
        binding2.imageView1.setOnClickListener {
            dialogView?.dismiss()
        }
        dialogView = dialog.create()
        dialogView?.show()

    }


    private fun attachObserve() {
        kajianViewModel.ShowKajian.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showKajian(it) })
        kajianViewModel.isError.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showError(it) })
        kajianViewModel.AddKajian.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showAddKajian(it) })
        kajianViewModel.UpdateKajian.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showUpdateKajian(it) })
        kajianViewModel.DeleteKajian.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showDeleteKajian(it) })
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(context, it?.message, Toast.LENGTH_SHORT).show()
    }

    private fun showAddKajian(

        it: Unit?
    ) {
        dialogView?.dismiss()
        Toast.makeText(context, "   Kajian berhasil disimpan", Toast.LENGTH_SHORT).show()
        kajianViewModel.showKajianView()
    }

    private fun showUpdateKajian(it: Unit?) {
        dialogView?.dismiss()
        Toast.makeText(context, "  Kajian berhasil diupdate", Toast.LENGTH_SHORT).show()
        kajianViewModel.showKajianView()
    }

    private fun showDeleteKajian(it: Unit?) {
        Toast.makeText(context, "  Kajian  berhasil dihapus", Toast.LENGTH_SHORT).show()
        kajianViewModel.showKajianView()
    }

}