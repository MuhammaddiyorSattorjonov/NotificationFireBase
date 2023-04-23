package com.example.notificationfirebase.retrofit


import com.example.notificationfirebase.models.MyResponse
import com.example.notificationfirebase.models.Sender
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers(
        "Content-type:application/json",
        "Authorization:key=AAAAoIJdPD8:APA91bFTX3qwqDBUMka2fIYbyMfGMSolitNJDdu7OBLiUGO7TLI9PugeY4_TKXp0Rm6P7dBwZbkngDrIY5aiDc82JZjNlEQ7bhpK0wDh8ht5SG6gIJhBs9ffik5q6ouoE85LVGtkKmpC"
    )
    @POST("fcm/send")
    fun sendNotification(@Body sender: Sender): Call<MyResponse>
}