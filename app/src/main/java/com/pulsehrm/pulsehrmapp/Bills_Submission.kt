package com.pulsehrm.pulsehrmapp

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bills__submission.*


class Bills_Submission : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills__submission)

       brows.setOnClickListener {

           val intent = Intent()
           intent.type = "image/*"
           intent.action = Intent.ACTION_GET_CONTENT

           startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1000)


            fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
               //super.onActivityResult(requestCode, resultCode, data)

                if (resultCode== Activity.RESULT_OK){


                }
           }

       }


        billspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2>0){
                    Toast.makeText(this@Bills_Submission,billspinner.selectedItem.toString(),Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}
