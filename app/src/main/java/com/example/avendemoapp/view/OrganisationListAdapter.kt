package com.example.avendemoapp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.avendemoapp.R
import com.example.avendemoapp.models.Organisation
import kotlinx.android.synthetic.main.activity_organisation_list.view.*

class OrganisationListAdapter(var list:ArrayList<Organisation>):RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    lateinit var context: Context
    private var onClickListener : OnClickListener?=null

    fun updateMovies(newOrganisation:List<Organisation>){
        list.clear()
        list.addAll(newOrganisation)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context =parent.context
        return ViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_organisation_list,parent,false))

    }

    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener = onClickListener
    }

    interface OnClickListener{
        fun onClick(position:Int ,model:Organisation)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if(holder is ViewHolder){
            holder.itemView.tv_name.text = model.name
            holder.itemView.tv_description.text = model.description ?: "No Description Available"
            //holder.itemView.

            holder.itemView.setOnClickListener{
                if(onClickListener!=null){
                    onClickListener!!.onClick(position,model)
                }
            }

            Glide
                .with(context)
                .load(model.image)
                .centerCrop()
                .into(holder.itemView.iv_user)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view)
}