package com.mouse.coustapol.db

import androidx.room.Dao
import androidx.room.Query
import com.mouse.coustapol.db.entity.Country
import com.mouse.coustapol.db.entity.State

@Dao
interface CoustapolDao {

    @Query("SELECT * FROM Country")
    fun getCountries(): List<Country>

    @Query("SELECT * FROM Country " +
            "WHERE UPPER(Name)=UPPER(:query) " +
            "OR UPPER(Abbreviate)=UPPER(:query)")
    fun findCountry(query: String): Country?

    @Query("SELECT * FROM State " +
            "WHERE UPPER(State.Country)=UPPER((SELECT Abbreviate FROM Country WHERE UPPER(Name)=UPPER(:country) OR UPPER(Abbreviate)=UPPER(:country))) " +
            "AND (UPPER(Name)=UPPER(:query) " +
            "OR UPPER(Abbreviate)=UPPER(:query) " +
            "OR ID = (SELECT StateID FROM AlternateNames WHERE UPPER(Name)=UPPER(:query)))")
    fun findState(country: String, query: String): State?

    @Query("SELECT * FROM State WHERE UPPER(State.Country)=UPPER(:country)")
    fun findCountryStates(country: String): List<State>

    @Query("SELECT Name FROM AlternateNames WHERE StateID=:stateId")
    fun findStateAlternateNames(stateId: Int): List<String>
}