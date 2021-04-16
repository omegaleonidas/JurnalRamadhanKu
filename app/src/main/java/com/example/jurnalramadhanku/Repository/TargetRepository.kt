package com.example.jurnalramadhanku.Repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.jurnalramadhanku.Local.model.DatabaseTarget
import com.example.jurnalramadhanku.Local.model.model.Target
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class TargetRepository(context: Context) {

    private var databaseConfig : DatabaseTarget? = null

    init {
        databaseConfig = DatabaseTarget.getInstance(context)
    }


    @SuppressLint("CheckResult")
    fun showTarget(responHandler: (List<Target>?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable() { databaseConfig?.targetDao()?.getAll() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 -> responHandler(it1) }
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun addDataTarget(
        item: Target,
        responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.targetDao()?.insert(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun updateTarget(item: Target, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.targetDao()?.update(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun deleteTarget(item: Target, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.targetDao()?.delete(item) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }


}