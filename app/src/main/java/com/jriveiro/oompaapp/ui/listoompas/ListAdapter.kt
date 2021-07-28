package com.jriveiro.oompaapp.ui.listoompas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jriveiro.oompaapp.R
import com.jriveiro.oompaapp.model.api.Result
import com.squareup.picasso.Picasso

class ListAdapter: RecyclerView.Adapter<ListAdapter.OompaHolder>() {

    var oompa : List<Result> = ArrayList()
    lateinit var context: Context

    fun ListOompasAdapter(oompa: List<Result>, context: Context) {
        this.oompa = oompa
        this.context = context
    }

    class OompaHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(contOompa: Result, context: Context){
            val txtName = view.findViewById(R.id.nameOompa) as TextView
            val imgProfile = view.findViewById(R.id.imageOompa) as ImageView
            val txtEmail = view.findViewById(R.id.emailOompa) as TextView
            val txtGender = view.findViewById(R.id.genderOompa) as TextView
            txtName.text = contOompa.first_name
            imgProfile.loadUrl(contOompa.image)
            txtEmail.text = contOompa.email
            if (contOompa.gender == "F"){
                txtGender.text = "Female"
            }else{
                txtGender.text = "Male"
            }
            itemView.setOnClickListener {
               
            }
        }
        fun ImageView.loadUrl(url: String) {
            Picasso.with(context).load(url).into(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OompaHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return OompaHolder(layoutInflater.inflate(R.layout.card_oompa, parent, false))
    }

    override fun onBindViewHolder(holder: OompaHolder, position: Int) {
        val item =oompa.get(position)
        holder.bind(item, context)
    }

    override fun getItemCount(): Int {
        return oompa.size
    }
}
