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
import com.example.jurnalramadhanku.Adapter.AdapterTarget
import com.example.jurnalramadhanku.Local.model.DatabaseTarget
import com.example.jurnalramadhanku.Local.model.model.Target
import com.example.jurnalramadhanku.R
import com.example.jurnalramadhanku.ViewModel.TargetViewModel
import kotlinx.android.synthetic.main.dialog_from_target.view.*
import kotlinx.android.synthetic.main.fragment_target.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TargetFragment : Fragment() {

    private var targetDatabase: DatabaseTarget? = null
    lateinit var targetViewModel: TargetViewModel


    private var dialogView: Dialog? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_target, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        targetViewModel.showTargetView()
        attachObserve()
        floatingActionButton.setOnClickListener {
            Toast.makeText(context, "button berhasil tertekan ", Toast.LENGTH_SHORT).show()
            getshowAddDialog()

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        targetViewModel = ViewModelProvider(this).get(TargetViewModel::class.java)
        targetDatabase = context?.let { DatabaseTarget.getInstance(it) }
    }

    private fun getshowAddDialog() {

        val dialog = AlertDialog.Builder(context)
        val view: View = layoutInflater.inflate(R.layout.dialog_from_target, null)
        dialog.setView(view)
        view.btnSave.setOnClickListener {


            val target = view.editTextTextPersonName.text.toString()

            val tanggal = getDate()


            targetViewModel.addTargetView(Target(null, target))


            //   dialogView?.dismiss()


        }
        view.imageView.setOnClickListener {
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

    private fun showJadwal(it: List<Target>?) {
        recyclerViewTarget.adapter = AdapterTarget(it, object : AdapterTarget.OnClickListener {
            override fun delete(item: Target?) {
                AlertDialog.Builder(context).apply {
                    setTitle("hapus")
                    setMessage("yakin hapus Target ?")
                    setCancelable(false)
                    setPositiveButton("yakin ") { dialogInterface, i ->
                        targetViewModel.deleteJadwalView(item!!)
                    }
                    setNegativeButton("batal") { dialogInterface, i ->
                        dialogInterface.dismiss()
                    }
                }.show()
            }

            override fun update(item: Target?) {
                getShowUpdateData(item)


            }

        })
    }


    private fun getShowUpdateData(item: Target?) {

        val dialog = AlertDialog.Builder(context)
        val view: View = layoutInflater.inflate(R.layout.dialog_from_target, null)
        dialog.setView(view)
        view.btnSave.text = "update"
        view.editTextTextPersonName.setText(item?.target)



        view.btnSave.setOnClickListener {
            val id = item?.id
            val target = view.editTextTextPersonName.text.toString()

            targetViewModel.updateTargetView(Target(id, target))

        }
        view.imageView.setOnClickListener {
            dialogView?.dismiss()
        }
        dialogView = dialog.create()
        dialogView?.show()

    }


    private fun attachObserve() {
        targetViewModel.ShowTarget.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showJadwal(it) })
        targetViewModel.isError.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showError(it) })
        targetViewModel.AddTarget.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showAddJadwal(it) })
        targetViewModel.UpdateTarget.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { showUpdateJadwal(it) })
        targetViewModel.DeleteTarget.observe(
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
        Toast.makeText(context, "Target   pelajaran berhasil disimpan", Toast.LENGTH_SHORT).show()
        targetViewModel.showTargetView()
    }

    private fun showUpdateJadwal(it: Unit?) {
        dialogView?.dismiss()
        Toast.makeText(context, "Target pelajran berhasil diupdate", Toast.LENGTH_SHORT).show()
        targetViewModel.showTargetView()
    }

    private fun showDeleteJadwal(it: Unit?) {
        Toast.makeText(context, "jadwal pelajaran  berhasil dihapus", Toast.LENGTH_SHORT).show()
        targetViewModel.showTargetView()
    }


}