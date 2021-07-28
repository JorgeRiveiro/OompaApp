package com.jriveiro.oompaapp.ui.listoompas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jriveiro.oompaapp.R
import com.jriveiro.oompaapp.db.OompasDB
import com.jriveiro.oompaapp.model.api.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListActivity : AppCompatActivity() {

    lateinit var mRecyclerView : RecyclerView
    val mAdapter : ListAdapter = ListAdapter()
    private lateinit var database: OompasDB
    var listaOompas = emptyList<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        database = OompasDB.getDatabase(this)

        startUI()
    }


    private fun startUI() {
        CoroutineScope(Dispatchers.IO).launch {
            listaOompas = database.oompaDao().getAllOompas()
            setUpRecyclerView()
        }
    }

    fun setUpRecyclerView() {
        mRecyclerView = findViewById(R.id.oompasRecyclerView) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter.ListOompasAdapter(listaOompas, this)
        mRecyclerView.adapter = mAdapter

    }
}