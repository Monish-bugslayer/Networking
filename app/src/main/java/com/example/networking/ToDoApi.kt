package com.example.networking

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ToDoApi {

    @GET("/todos")//request
    suspend fun getTodos(): Response<List<ToDoItems>>//get list of todos


    /*
    post request -> to post some data through remote server
    put request ->  update the data
    delete request -> to deted some data
    */

//    @POST("/createTodo")
//    fun postTodo(@Body todo:ToDo):Response<CreateToDoResponse>
}