package com.example.avendemoapp.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.avendemoapp.R
import com.example.avendemoapp.models.Organisation
import com.example.avendemoapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var mAdapter=OrganisationListAdapter(arrayListOf())
    lateinit var mViewModel:ListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_view.setHasFixedSize(true)
        rv_view.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_view.adapter = mAdapter

        mViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        mViewModel.refresh()

        mAdapter.setOnClickListener(object : OrganisationListAdapter.OnClickListener {
            override fun onClick(position: Int, model: Organisation) {

                mViewModel.fetchGithhubDetails(model.name ?: "")

            }
        })

        observeViewModel()
        observeGitHubUrl()

    }


    private fun observeViewModel(){
        mViewModel.organisation
            .observe(this, Observer { org ->
                org?.let {
                    rv_view.visibility = View.VISIBLE
                    mAdapter.updateMovies(org)
                }

            })
    }

    private fun observeGitHubUrl(){
        mViewModel.githubUrl
            .observe(this, Observer { org ->
                org?.let {
                    rv_view.visibility = View.VISIBLE
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(org.githubUrl))
                    startActivity(browserIntent)
                }

            })
    }


}