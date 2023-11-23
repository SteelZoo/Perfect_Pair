package com.olduo.last_dance.data.datasource.remote.service

import com.olduo.last_dance.data.model.GroupDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface GroupService {

    @POST("group/{userId}")
    suspend fun craeteGroup(@Body groupDto: GroupDto, @Path("userId") userId: String): Response<Boolean>

    @GET("group/list/{userId}")
    suspend fun getUserGroups(@Path("userId") userId: String): Response<List<GroupDto>>

    @POST("group/{code}/{userId}")
    suspend fun joinGroup(@Path("code") code: String, @Path("userId") userId: String): Response<Boolean>

}