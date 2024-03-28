package com.photon.findmyip.repository

import com.photon.findmyip.model.Location
import retrofit2.Response

interface FindIpRepository {

    suspend fun fetchData(ip: String, format: String): Response<Location>
}