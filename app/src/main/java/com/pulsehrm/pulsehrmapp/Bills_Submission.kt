package com.pulsehrm.pulsehrmapp

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.app.ProgressDialog
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
import kotlinx.android.synthetic.main.activity_bills__submission.*


class Bills_Submission : AppCompatActivity() {

    val PERMISSION_REQUEST_CODE = 1001
    val PICK_IMAGE_REQUEST = 900;
    lateinit var filePath: Uri

    @TargetApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bills__submission)

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
                uploadFile()

                var contentUri = data.data

                preview.setImageURI(contentUri)

            }
        }
    }

    private fun uploadFile() {
        val progress = ProgressDialog(this).apply {
            setTitle("Uploading Picture....")
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            show()
            dismiss()
        }
    }
}
