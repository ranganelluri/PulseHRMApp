package com.pulsehrm.pulsehrmapp.Beans

import com.google.gson.annotations.SerializedName

data class LoginBean(@SerializedName("Message")
                    val message: String = "",
                     @SerializedName("Schema")
                    val schema: String = "",
                     @SerializedName("Email ID")
                    val emailID: String = "",
                     @SerializedName("Employee Number")
                    val employeeNumber: String = "",
                     @SerializedName("AID LOGIN")
                    val aidLogin: Int = 0,
                     @SerializedName("Company Code")
                    val companyCode: String = "",
                     @SerializedName("Flag")
                    val flag: Int = 0,
                     @SerializedName("Punch Flag")
                    val punchFlag: Int = 0)