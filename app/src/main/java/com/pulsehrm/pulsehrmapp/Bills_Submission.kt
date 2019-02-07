package com.pulsehrm.pulsehrmapp

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.pulsehrm.pulsehrmapp.Beans.BillSubmissionBean
import kotlinx.android.synthetic.main.activity_bills__submission.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

var mimeType :String = ""
var contentUri :Uri = Uri.EMPTY
var requestfile :String =  ""
var file:File = File(requestfile)
var mediapath:String =""


class Bills_Submission : AppCompatActivity() {

    val PERMISSION_REQUEST_CODE = 1001
    val PICK_IMAGE_REQUEST = 900
    private lateinit var filePath: Uri

    @TargetApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills__submission)


        upload.setOnClickListener {

            var imgfile = File(mediapath)

            var filecontent:RequestBody = RequestBody.create(MediaType.parse("Image/*"),imgfile)

            var fileupload = MultipartBody.Part.createFormData("file", file.name, filecontent)

            var filename:RequestBody = RequestBody.create(MediaType.parse("text/plain"), file.name)

            var r = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("https://app.pulsehrm.com/ords/evam_pulse/").build()

            var api = r.create(BillSubmissionAPI::class.java)

           var bundle = Intent().extras

           // var emp_no = bundle.getString("Emp_No")

            //var call = api.submitBills("ACMEI111",mimeType,R.id.ammount.toString(),R.id.billspinner.toString(),R.id.billdate.toString(),R.id.remarks.toString(),fileupload,"Bill")

            var call = api.submitBills("ACMEI111","image/png","2300","Fuel Bils","11/27/2018","Bills",fileupload,"Bill")


            call.enqueue(object:Callback<BillSubmissionBean> {
                override fun onFailure(call: Call<BillSubmissionBean>, t: Throwable) {
                    Toast.makeText(this@Bills_Submission,"Insert Failed",Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<BillSubmissionBean>, response: Response<BillSubmissionBean>) {
                    if (response.isSuccessful){
                        var bean = response.body()
                        var templist = mutableListOf<String>()
                        templist.add("message"+bean!!.message)

                        Toast.makeText(this@Bills_Submission,bean.message,Toast.LENGTH_LONG).show()
                    }

                    else{
                        Toast.makeText(this@Bills_Submission,"server error",Toast.LENGTH_LONG).show()
                    }
                }

            })
        }



        brows.setOnClickListener {
            when {
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) -> {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
                    } else {
                        chooseFile()
                    }
                }
                else -> chooseFile()
            }
        }


        billspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 > 0) {
                    Toast.makeText(this@Bills_Submission, billspinner.selectedItem.toString(), Toast.LENGTH_LONG).show()
                }
            }

        }
    }

    private fun chooseFile() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, "permission Denied!", Toast.LENGTH_LONG).show()
                else {
                    chooseFile()
                }
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode !== Activity.RESULT_OK) {
            return;
        }
        when (requestCode) {
            PICK_IMAGE_REQUEST -> {
                filePath = data!!.data

                 file = File(data.data.path)

                var requestfile = RequestBody.create(MediaType.parse("Image/*"), file)

                mimeType = contentResolver.getType(filePath)

                var contentUri = data.data

                preview.setImageURI(contentUri)

            }
        }
    }

}
