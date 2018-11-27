package com.pulsehrm.pulsehrmapp

import com.pulsehrm.pulsehrmapp.Beans.BillSubmissionBean
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface BillSubmissionAPI {

    @Multipart
    @POST("Expenses_Claim/submit_page")
    fun submitBills(@Header("EMP_NO") EMP_NO:String,
                    @Header("Content-Type") content_type:String,
                    @Header("BILL_AMOUNT") BILL_AMOUNT:String,
                    @Header("BILL_CATEGORY") BILL_CATEGORY:String,
                    @Header("BILL_DATE") BILL_DATE:String,
                    @Header("DESCRIPTION") DESCRIPTION:String,
                    @Part part: MultipartBody.Part,
                    @Header("File_Name") File_Name:String): Call<BillSubmissionBean>
}