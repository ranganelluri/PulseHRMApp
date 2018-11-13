package com.pulsehrm.pulsehrmapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.pulsehrm.pulsehrmapp.Beans.LoginBean
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        signin.setOnClickListener {
            val r = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://123.176.42.94:8084/ords/pulse_dev/").build()
            val api = r.create(LoginAPI::class.java)

            val call = api.doLogin(username.text.toString(),password.text.toString())

            call.enqueue(object : Callback<LoginBean> {
                override fun onFailure(call: Call<LoginBean>, t: Throwable) {

                    Toast.makeText(this@MainActivity,"Fail",Toast.LENGTH_LONG).show()

                    }

                override fun onResponse(call: Call<LoginBean>, response: Response<LoginBean>) {

                    Toast.makeText(this@MainActivity,"success",Toast.LENGTH_LONG).show()

                    val bean = response.body()

                    val tempList = mutableListOf<String>()

                    tempList.add("message:"+bean!!.message)

                    Toast.makeText(this@MainActivity,bean.message,Toast.LENGTH_LONG).show()

                }

            })
        }
    }
}
