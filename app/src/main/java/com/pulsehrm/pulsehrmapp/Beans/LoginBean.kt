package com.pulsehrm.pulsehrmapp.Beans

import com.google.gson.annotations.SerializedName

data class LoginBean(@SerializedName("MESSAGE")
                     val message: String = "",
                     @SerializedName("SUCCESS_FLAG")
                     val successFlag: String = "",
                     @SerializedName("SCHEMA")
                     val schema: String = "",
                     @SerializedName("AID_LOGIN")
                     val aidLogin: String = "",
                     @SerializedName("COMPANY_CODE")
                     val companyCode: String = "",
                     @SerializedName("EMP_NO")
                     val empNo: String = "",
                     @SerializedName("EMAIL_ID")
                     val emailId: String = "",
                     @SerializedName("PUNCH_FLAG")
                     val punchFlag: String = "")