package com.example.avendemoapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.avendemoapp.models.Organisation
import com.example.avendemoapp.models.OrganisationGithubUrl
import com.example.avendemoapp.models.OrganisationService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel:ViewModel() {

    val organisation = MutableLiveData<List<Organisation>>()
    val githubUrl = MutableLiveData<OrganisationGithubUrl>()
    val organisationService = OrganisationService()
    val disposable = CompositeDisposable()


    fun refresh(){
        fetchDetails()
    }


    fun fetchDetails(){
        disposable.add(
            organisationService.getOrganisationList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Organisation>>(){
                    override fun onSuccess(value: List<Organisation>?) {
                            organisation.value = value
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("Error","Error in RxJava")
                    }

                })
        )
    }

    fun fetchGithhubDetails(name:String){
        disposable.add(
            organisationService.getGithubUrl(name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<OrganisationGithubUrl>(){
                    override fun onSuccess(value: OrganisationGithubUrl?) {
                        githubUrl.value = value
                        //githubUrl.value.githubUrl
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("Error",e.toString())
                    }

                })
        )
    }



    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}