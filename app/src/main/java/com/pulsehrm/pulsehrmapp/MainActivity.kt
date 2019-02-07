package com.pulsehrm.pulsehrmapp


import android.content.Intent
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
            var r = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://app.pulsehrm.com/ords/evam_pulse/").build()
            var api = r.create(LoginAPI::class.java)

            var username = username.text.toString()
            var password = password.text.toString()

            var call = api.doLogin(username,password)


            call.enqueue(object : Callback<LoginBean> {
                override fun onFailure(call: Call<LoginBean>, t: Throwable) {

                    Toast.makeText(this@MainActivity,"Fail",Toast.LENGTH_LONG).show()

                    }

                override fun onResponse(call: Call<LoginBean>, response: Response<LoginBean>) {

                     var bean = response.body()

                     var tempList = mutableListOf<String>()

                     tempList.add("message:"+bean!!.message)

                    var i = Intent(this@MainActivity,Bills_Submission::class.java)


                  /*  var bundle = Bundle()
                    bundle.putString("Emp_No",bean.empNo)
                    i.putExtras(bundle)
                    startActivity(i)  */

                    if (bean.successFlag.toInt() == 1){

                        var intent = Intent(this@MainActivity,Bills_Submission::class.java)
                        startActivity(intent)

                        Toast.makeText(this@MainActivity,bean.message,Toast.LENGTH_LONG).show()

                    }
                    else

                        Toast.makeText(this@MainActivity,bean.message,Toast.LENGTH_LONG).show()

                }

            })
        }
    }
}
