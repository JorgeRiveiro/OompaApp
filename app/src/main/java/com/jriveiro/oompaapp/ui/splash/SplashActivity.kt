package com.jriveiro.oompaapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jriveiro.oompaapp.R
import com.jriveiro.oompaapp.db.OompasDB
import com.jriveiro.oompaapp.model.api.Result
import com.jriveiro.oompaapp.ui.listoompas.ListActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private lateinit var  viewModel: SplashViewModel
private lateinit var database: OompasDB
var listaOompas= emptyList<Result>()

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        database= OompasDB.getDatabase(this)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        initUI()
    }

    private fun initUI(){

        viewModel.getOompaList()

        viewModel.oompaList.observe(this, Observer { list ->
            listaOompas = list.results
            startDB()
        })
    }

    private fun startDB(){
        CoroutineScope(Dispatchers.IO).launch {
            val listaDataContributors= database.oompaDao().getAllOompas()
            if (listaDataContributors != listaOompas){
                database.oompaDao().insert(listaOompas)
                startActivity(ListActivity::class.java)
            }else{
                startActivity(ListActivity::class.java)
            }

        }
    }

    fun startActivity(activity: Class<*>) {
        val startActivity = Intent(this, activity)
        startActivity(startActivity)
        finish()
    }
}