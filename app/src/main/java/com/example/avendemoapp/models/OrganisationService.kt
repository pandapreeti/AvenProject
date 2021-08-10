package com.example.avendemoapp.models

import com.example.avendemoapp.util.Constants
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class OrganisationService {


    fun getOrganisationList():Single<List<Organisation>>{
        val api =  Retrofit.Builder()
            .baseUrl(Constants.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(OrganisationApi::class.java)

        return api.getOrganisationDetails()
    }

    fun getGithubUrl(name :String):Single<OrganisationGithubUrl>{
        val api =  Retrofit.Builder()
            .baseUrl(Constants.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(OrganisationApi::class.java)


        return api.getGitUrl(name)
    }
}