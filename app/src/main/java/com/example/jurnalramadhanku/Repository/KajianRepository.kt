package com.example.jurnalramadhanku.Repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.jurnalramadhanku.Local.model.DatabaseTarget
import com.example.jurnalramadhanku.Local.model.model.Kajian
import com.example.jurnalramadhanku.Local.model.model.Target
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class KajianRepository(context: Context)  {
    private var databaseConfig : DatabaseTarget? = null

    init {
        databaseConfig = DatabaseTarget.getInstance(context)
    }


    @SuppressLint("CheckResult")
    fun showkajian(responHandler: (List<Kajian>?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable() { databaseConfig?.kajianDao()?.getAll() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 -> responHandler(it1) }
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun addDataKajian(
        item: Kajian,
        responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.kajianDao()?.insert(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun updateKajian(item: Kajian, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.kajianDao()?.update(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun deleteKajian(item: Kajian, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.kajianDao()?.delete(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }




}