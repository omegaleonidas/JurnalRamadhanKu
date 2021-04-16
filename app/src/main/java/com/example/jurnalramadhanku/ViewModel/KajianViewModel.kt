package com.example.jurnalramadhanku.ViewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.jurnalramadhanku.Local.model.DatabaseTarget
import com.example.jurnalramadhanku.Local.model.model.Kajian
import com.example.jurnalramadhanku.Local.model.model.Target
import com.example.jurnalramadhanku.Repository.KajianRepository
import com.example.jurnalramadhanku.Repository.TargetRepository

class KajianViewModel(application: Application) : AndroidViewModel(application) {

    private var databaseTarget: DatabaseTarget? = null
    val repository = KajianRepository(application.applicationContext)
    var context: Context = application

    var ShowKajian = MutableLiveData<List<Kajian>?>()
    var isError = MutableLiveData<Throwable>()
    var AddKajian = MutableLiveData<Unit>()
    var UpdateKajian = MutableLiveData<Unit>()
    var DeleteKajian = MutableLiveData<Unit>()

    fun showKajianView() {
        repository.showkajian({
            ShowKajian.value = it
        }, {
            isError.value = it
        })
    }

    fun addKajianView(item: Kajian){

        if (item.time?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else if (item.judul?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        } else if (item.pemateri?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        } else if (item.lokasi?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        } else if (item.quote?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }



        else {
            repository.addDataKajian(item, {

                AddKajian.value = it
            }, {
                isError.value = it
            })
        }

    }

    fun updateKajianView(item: Kajian) {
        if (item.time?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else if (item.judul?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        } else if (item.pemateri?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        } else if (item.lokasi?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        } else if (item.quote?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else {

            repository.updateKajian(item, {
                UpdateKajian.value = it
            }, {
                isError.value = it
            })
        }
    }

    fun deleteKajianView(item: Kajian) {
        repository.deleteKajian(item, {
            DeleteKajian.value = it
        }, {
            isError.value = it
        })
    }


}