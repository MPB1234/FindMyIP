package com.photon.findmyip.repository

import com.photon.findmyip.FindIPApiService
import com.photon.findmyip.model.Location
import com.photon.findmyip.model.Status
import retrofit2.Response
import javax.inject.Inject

class FindIpRepositoryImpl @Inject constructor(private val apiService: FindIPApiService) : FindIpRepository {

    override suspend fun fetchData(ip:String,format:String): Response<Location> {
        println("==>> IMPL IP:$ip  and format is : $format")
       return apiService.fetchData(ip,format)
    }
}