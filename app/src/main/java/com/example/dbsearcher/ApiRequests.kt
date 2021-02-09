package com.example.dbsearcher

import com.example.dbsearcher.api.PersonJson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ApiRequests {
    @Headers("Authorization: ENV!!!!")
    @GET("/get_employees")
    fun getEmployeesPages(): Call<ArrayList<PersonJson>>
}