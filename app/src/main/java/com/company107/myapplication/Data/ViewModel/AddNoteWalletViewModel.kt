package com.company107.myapplication.Data.ViewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import java.util.*

class AddNoteWalletViewModel : ViewModel {

    var sumber_uang = ObservableField("")
    var penggunaan = ObservableField("")
    var jumlah_uang = ObservableField("")


    constructor() : super()
}