package com.example.avendemoapp.models

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface OrganisationApi {

    @GET("organizations")
    fun getOrganisationDetails(): Single<List<Organisation>>


    @GET("orgs/{orgID}")
    fun getGitUrl(@Path("orgID") orgID:String):Single<OrganisationGithubUrl>

}