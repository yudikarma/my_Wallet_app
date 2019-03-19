package com.company107.myapplication.Data.Model

import com.google.gson.annotations.SerializedName

 class Data {
    constructor(
       id: String?,
       jenis_pengeluaran: String?,
       jumlah_uang: String?,
       sumber_uang: String?,
       time: String?,
       penggunaan: String?

    ) {
       this.id = id
       this.jenis_pengeluaran = jenis_pengeluaran
       this.jumlah_uang = jumlah_uang
       this.sumber_uang = sumber_uang
       this.time = time
       this.penggunaan = penggunaan
    }

    var id: String? = null
    var jenis_pengeluaran: String? = null
    var jumlah_uang: String? = null
    var sumber_uang: String? = null
    var time: String? = null
    var penggunaan: String? = null
}