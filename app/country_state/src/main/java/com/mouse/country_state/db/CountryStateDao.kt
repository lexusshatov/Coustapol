package com.mouse.country_state.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CountryStateDao {

    @Query("SELECT * FROM Country WHERE Name=:query OR Abbreviate=:query")
    fun findCountry(query: String): Country?

    @Query("SELECT * FROM State WHERE Name=:query OR Abbreviate=:query")
    fun findState(query: String): State?
}