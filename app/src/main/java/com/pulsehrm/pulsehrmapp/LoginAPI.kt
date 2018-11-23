package com.pulsehrm.pulsehrmapp
import com.pulsehrm.pulsehrmapp.Beans.LoginBean
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginAPI {
    @FormUrlEncoded
    @POST("Mob_C_Login/login_page")
    fun doLogin(@Field("USERNAME") USERNAME:String,
                @Field("PASSWORD") PASSWORD:String) :Call<LoginBean>
}