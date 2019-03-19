package com.company107.myapplication.Data.Network

import com.company107.myapplication.BuildConfig
import com.company107.myapplication.Data.Model.Data
import com.company107.myapplication.Data.Model.Notes
import com.company107.myapplication.Data.Model.responseModel
import retrofit2.Call
import retrofit2.http.*

interface getDataWalletInterface {
    //view
    @GET(BuildConfig.BASE_URL+BuildConfig.VIEW_DATA)
    fun GetDataWallet(/*seharusnya ada header atau body*/) :Call<Notes>

    //create
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+BuildConfig.CREATE_DATA)
    fun CreateDataWallet(
        @Field("jenis_pengeluaran")jenis_pengeluaran:String,
        @Field("jumlah_uang") jumlah_uang:String,
        @Field("sumber_uang") sumber_uang:String,
        @Field("time") time:String,
        @Field("penggunaan") penggunaan:String
    ):Call<responseModel>

    //update
    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+BuildConfig.UPDATE_DATA)
    fun UpdateDataWallet(
        @Field("id")id: Int,
        @Field("jenis_pengeluaran")jenis_pengeluaran:String,
        @Field("jumlah_uang") jumlah_uang:String,
        @Field("sumber_uang") sumber_uang:String,
        @Field("time") time:String,
        @Field("penggunaan") penggunaan:String
    ):Call<responseModel>

    @FormUrlEncoded
    @POST(BuildConfig.BASE_URL+BuildConfig.DELETE_DATA)
    fun DeleteDataWallet(
        @Field("id")id:String

    ):Call<responseModel>



}