package com.example.dbsearcher
import com.example.dbsearcher.api.PersonJson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiRequests {
    @Headers("Authorization: ")
    @GET("/get_employees")
    fun getEmployeesPages(): Call<ArrayList<PersonJson>>

    @FormUrlEncoded
    @Headers("Authorization: ")
    @POST("/find_employee")
    fun findEmployee(
    @Field("fname") fname: String?,
    @Field("lname") lname: String?
    ): Call<ArrayList<PersonJson>>

}