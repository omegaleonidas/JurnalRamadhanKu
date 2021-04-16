package com.example.jurnalramadhanku.ViewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.jurnalramadhanku.Local.model.DatabaseTarget
import com.example.jurnalramadhanku.Local.model.model.Target
import com.example.jurnalramadhanku.Repository.TargetRepository

class TargetViewModel(application: Application) : AndroidViewModel(application) {

    private var databaseTarget: DatabaseTarget? = null
    val repository = TargetRepository(application.applicationContext)
    var context: Context = application

    var ShowTarget = MutableLiveData<List<Target>?>()
    var isError = MutableLiveData<Throwable>()
    var AddTarget = MutableLiveData<Unit>()
    var UpdateTarget = MutableLiveData<Unit>()
    var DeleteTarget = MutableLiveData<Unit>()

    fun showTargetView() {
        repository.showTarget({
            ShowTarget.value = it
        }, {
            isError.value = it
        })
    }

    fun addTargetView(item: Target){

        if (item.target?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }    else {
            repository.addDataTarget(item, {

                AddTarget.value = it
            }, {
                isError.value = it
            })
        }

    }

    fun updateTargetView(item: Target) {
        if (item.target?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else {

            repository.updateTarget(item, {
                UpdateTarget.value = it
            }, {
                isError.value = it
            })
        }
    }

    fun deleteJadwalView(item: Target) {
        repository.deleteTarget(item, {
            DeleteTarget.value = it
        }, {
            isError.value = it
        })
    }
}