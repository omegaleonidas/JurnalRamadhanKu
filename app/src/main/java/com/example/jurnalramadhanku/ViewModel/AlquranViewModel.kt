package com.example.jurnalramadhanku.ViewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.jurnalramadhanku.Local.model.DatabaseTarget
import com.example.jurnalramadhanku.Local.model.model.Alquran
import com.example.jurnalramadhanku.Local.model.model.Kajian
import com.example.jurnalramadhanku.Repository.AlquranRepository
import com.example.jurnalramadhanku.Repository.KajianRepository

class AlquranViewModel (application: Application) : AndroidViewModel(application) {



    private var databaseTarget: DatabaseTarget? = null
    val repository = AlquranRepository(application.applicationContext)
    var context: Context = application

    var ShowAlquran = MutableLiveData<List<Alquran>?>()
    var isError = MutableLiveData<Throwable>()
    var AddAlquran = MutableLiveData<Unit>()
    var UpdateAlquran = MutableLiveData<Unit>()
    var DeleteAlquran = MutableLiveData<Unit>()

    fun showAlquranView() {
        repository.showAlquran({
            ShowAlquran.value = it
        }, {
            isError.value = it
        })
    }

    fun addAlquranView(item: Alquran){

        if (item.tanggal?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else if (item.tilawah?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        } else if (item.menghafal?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        } else if (item.murajaan?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }
        else {
            repository.addDataAlquran(item, {

                AddAlquran.value = it
            }, {
                isError.value = it
            })
        }

    }

    fun updateAlquranView(item: Alquran) {
        if (item.tanggal?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else if (item.tilawah?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        } else if (item.menghafal?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        } else if (item.murajaan?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }
        else {

            repository.updateAlquran(item, {
                UpdateAlquran.value = it
            }, {
                isError.value = it
            })
        }
    }

    fun deleteAlquranView(item: Alquran) {
        repository.deleteAlquran(item, {
            DeleteAlquran.value = it
        }, {
            isError.value = it
        })
    }

}