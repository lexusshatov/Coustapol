package com.mouse.coustapol.db

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CoustapolDao {

    @Query("SELECT * FROM Country WHERE Name=:query OR Abbreviate=:query")
    fun findCountry(query: String): Country?

    @Query("SELECT * FROM State WHERE State.Country=:country")
    fun findCountryStates(country: String): List<State>

    @Query("SELECT * FROM State WHERE State.Country=(SELECT Abbreviate FROM Country WHERE Name=:country OR Abbreviate=:country) AND (Name=:query OR Abbreviate=:query)")
    fun findState(country: String, query: String): State?

    @Query("SELECT * FROM Country")
    fun getCountries(): List<Country>
}