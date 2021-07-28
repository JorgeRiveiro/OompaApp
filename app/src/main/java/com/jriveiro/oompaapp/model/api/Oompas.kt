package com.jriveiro.oompaapp.model.api

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Oompas(
    val current: Int,
    val results: List<Result>,
    val total: Int
)

@Entity
data class Result(
    val age: Int,
    val country: String,
    val email: String,
    @Embedded val favorite: Favorite,
    val first_name: String,
    val gender: String,
    val height: Int,
    @PrimaryKey @ColumnInfo val id: Int,
    val image: String,
    val last_name: String,
    val profession: String
)

data class Favorite(
    val color: String,
    val food: String,
    val random_string: String,
    val song: String
)