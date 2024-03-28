package com.photon.findmyip

import com.photon.findmyip.model.Location
import com.photon.findmyip.model.Status
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FindIPApiService {

    @GET("{ip}/{format}/")
    suspend fun fetchData(
        @Path("ip") publicIp: String,
        @Path("format") format: String
    ): Response<Location>

}