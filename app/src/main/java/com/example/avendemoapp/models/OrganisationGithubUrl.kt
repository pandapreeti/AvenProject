package com.example.avendemoapp.models

import com.google.gson.annotations.SerializedName

data class OrganisationGithubUrl(
    @SerializedName("html_url")
    var githubUrl:String?
)
