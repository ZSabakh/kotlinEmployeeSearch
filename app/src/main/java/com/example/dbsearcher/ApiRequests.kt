package com.example.dbsearcher
import com.example.dbsearcher.api.PersonJson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiRequests {
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZmVmZTQ3NTM2Yjc1NDM1ZjA1NTI0ODgiLCJpYXQiOjE2MDk1NTcxMzB9.WOI1eYCSRjIjLf-qRLNR9JVa1RnfIZsxf-YzesLhLEg")
    @GET("/get_employees")
    fun getEmployeesPages(): Call<ArrayList<PersonJson>>

    @FormUrlEncoded
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZmVmZTQ3NTM2Yjc1NDM1ZjA1NTI0ODgiLCJpYXQiOjE2MDk1NTcxMzB9.WOI1eYCSRjIjLf-qRLNR9JVa1RnfIZsxf-YzesLhLEg")
    @POST("/find_employee")
    fun findEmployee(
    @Field("fname") fname: String?,
    @Field("lname") lname: String?
    ): Call<ArrayList<PersonJson>>

}