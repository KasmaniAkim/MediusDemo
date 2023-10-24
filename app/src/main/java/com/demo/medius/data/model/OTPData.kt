package com.demo.medius.data.model

import com.google.gson.annotations.SerializedName

data class OTPData(
    @SerializedName("id") var id: String? = null,
    @SerializedName("branch_id") var branchId: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("phone") var phone: String? = null,
    @SerializedName("emp_id") var empId: String? = null,
    @SerializedName("type_id") var typeId: String? = null,
    @SerializedName("created_at") var createdAt: String? = null,
    @SerializedName("updated_at") var updatedAt: String? = null,
    @SerializedName("deleted_at") var deletedAt: String? = null,
    @SerializedName("is_admin") var isAdmin: String? = null,
    @SerializedName("address") var address: String? = null,
    @SerializedName("branch_name") var branchName: String? = null
)