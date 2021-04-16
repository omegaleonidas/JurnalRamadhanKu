package com.example.jurnalramadhanku.Repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.jurnalramadhanku.Local.model.DatabaseTarget
import com.example.jurnalramadhanku.Local.model.model.Alquran
import com.example.jurnalramadhanku.Local.model.model.Kajian
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AlquranRepository(context: Context)  {
    private var databaseConfig : DatabaseTarget? = null

    init {
        databaseConfig = DatabaseTarget.getInstance(context)
    }


    @SuppressLint("CheckResult")
    fun showAlquran(responHandler: (List<Alquran>?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable() { databaseConfig?.alquranDao()?.getAll() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 -> responHandler(it1) }
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun addDataAlquran(
        item: Alquran,
        responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.alquranDao()?.insert(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun updateAlquran(item: Alquran, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.alquranDao()?.update(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun deleteAlquran(item: Alquran, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.alquranDao()?.delete(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }




}