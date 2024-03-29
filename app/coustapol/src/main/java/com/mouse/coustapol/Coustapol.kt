package com.mouse.coustapol

import android.content.Context
import androidx.room.Room
import com.mouse.coustapol.db.CoustapolDao
import com.mouse.coustapol.db.CoustapolDatabase
import com.mouse.coustapol.db.entity.Country
import com.mouse.coustapol.db.entity.State

object Coustapol : CoustapolDao {

    private const val DATABASE_NAME = "CountryStates.db"

    private lateinit var database: CoustapolDatabase
    private val dao: CoustapolDao
        get() = database.coustapolDao()

    fun init(appContext: Context) {
        appContext.deleteDatabase(DATABASE_NAME)
        database = Room.databaseBuilder(
            appContext,
            CoustapolDatabase::class.java,
            DATABASE_NAME
        )
            .createFromAsset(DATABASE_NAME)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun findCountry(query: String): Country? {
        return dao.findCountry(query)
    }

    override fun findState(country: String, query: String): State? {
        return if (query.isNotEmpty()) dao.findState(country, query) else null
    }

    override fun findCountryStates(country: String): List<State> {
        val countryData: Country = findCountry(country) ?: return emptyList()
        return dao.findCountryStates(countryData.abbreviate)
    }

    override fun getCountries(): List<Country> {
        return dao.getCountries()
    }

    override fun findStateAlternateNames(stateId: Int): List<String> {
        return dao.findStateAlternateNames(stateId)
    }
}