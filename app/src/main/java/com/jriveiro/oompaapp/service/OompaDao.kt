package com.jriveiro.oompaapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jriveiro.oompaapp.model.api.Result

@Dao
interface OompaDao {
    @Query("SELECT * FROM RESULT")
    fun getAllOompas(): List<Result>

    @Insert
    fun insert(oompas: List<Result>)
}