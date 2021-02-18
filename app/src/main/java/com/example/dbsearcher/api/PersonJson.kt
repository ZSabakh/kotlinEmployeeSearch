package com.example.dbsearcher.api

import java.io.Serializable

data class PersonJson(
    val birth_date: String,
    val citizenship_code: String,
    val first_name: String,
    val gender: Int,
    val id: Int,
    val image_code: String,
    val last_name: String,
    val living_place: String,
    val person_status: Int,
    val private_number: String,
    val region: String
) : Serializable