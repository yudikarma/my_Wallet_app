package com.company107.myapplication.UI

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.company107.myapplication.BR
import com.company107.myapplication.Data.Model.Data
import com.company107.myapplication.Data.Model.Notes
import com.company107.myapplication.Data.Model.responseModel
import com.company107.myapplication.Data.Network.RetrofitBuilder
import com.company107.myapplication.Data.Network.getDataWalletInterface
import com.company107.myapplication.Data.Presenter.SimpanNotes
import com.company107.myapplication.Data.ViewModel.AddNoteWalletViewModel
import com.company107.myapplication.R
import com.company107.myapplication.databinding.ActivityAddNoteBinding
import kotlinx.android.synthetic.main.activity_add_note.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddNoteActivity : AppCompatActivity() {
    //private lateinit var binding:ActivityAddNoteBinding
    private lateinit var jenis_pengeluaran:String
    private lateinit var add_or_edit:String
    private var id:Int? = null
    private var sumber_uang_s:String? = null
    private var penggunaan_s:String? = null
    private var jumlah_uang_s:String? = null
    private var time_s:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_add_note)


        val binding = DataBindingUtil.setContentView<ActivityAddNoteBinding>(this@AddNoteActivity,R.layout.activity_add_note)
       // var addnotemodel = ViewModelProviders.of(this).get(AddNoteWalletViewModel::class.java)

        val bundle:Bundle? = intent.extras
        jenis_pengeluaran = bundle?.getString("request_type").toString()
        add_or_edit = bundle?.getString("add_or_edit").toString()
        id = bundle?.getInt("id")
        sumber_uang_s = bundle?.getString("sumber_uang")
        penggunaan_s = bundle?.getString("penggunaan")
        jumlah_uang_s = bundle?.getString("jumlah_uang")
        time_s = bundle?.getString("time")


        if (add_or_edit.equals("edit")){
            btn_delete.visibility = View.VISIBLE
            val data = Data(id.toString(),jenis_pengeluaran,jumlah_uang_s,sumber_uang_s,time_s,penggunaan_s)
            binding.setVariable(BR.addnotesviewmodel,data)
            binding.executePendingBindings()

        }

        btn_next.setOnClickListener {
            if (add_or_edit.contentEquals("add")) {
                simpanData()
            }else{
                updateData(id!!)
            }
        }

        btn_delete.setOnClickListener{
            deleteData(id!!)
        }



        /*object : SimpanNotes {
            override fun onSaveClick() {
                var sumber_uang:String = sumber_uang.text.toString()
                var penggunaan:String = penggunaan.text.toString()
                var jumlah_uang:String = jumlah_uang.text.toString()

                Toast.makeText(this@AddNoteActivity,""+sumber_uang, Toast.LENGTH_SHORT).show()
                super.onSaveClick()
            }
        }*/
    }
    fun simpanData(){

        sumber_uang_s = sumber_uang.text.toString()
        penggunaan_s = penggunaan.text.toString()
        jumlah_uang_s = jumlah_uang.text.toString()
        time_s = "01:09:1990"

        val getdatawallet = RetrofitBuilder.builder(this!!.applicationContext).create(getDataWalletInterface::class.java)
        val callgetdata = getdatawallet.CreateDataWallet(jenis_pengeluaran,jumlah_uang_s.toString(),sumber_uang_s.toString(),penggunaan_s.toString(),time_s.toString())
        callgetdata.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
               // Toast.makeText(this@AddNoteActivity,"failure",Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+t.message);
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                //Toast.makeText(activity,"succes",Toast.LENGTH_SHORT).show()
                val response = response.body() as responseModel
                System.out.println("test : "+response.message)

                //val resultIntent = Intent()
                //setResult(Activity.RESULT_OK,resultIntent)
                finish()


            }

        })

    }

    fun updateData(id:Int){
         sumber_uang_s = sumber_uang.text.toString()
         penggunaan_s = penggunaan.text.toString()
         jumlah_uang_s = jumlah_uang.text.toString()
         time_s = "01:01:1990";

        val getdatawallet = RetrofitBuilder.builder(this!!.applicationContext).create(getDataWalletInterface::class.java)
        val callUpdatedata = getdatawallet.UpdateDataWallet(id,jenis_pengeluaran,jumlah_uang_s.toString(),sumber_uang_s.toString(),penggunaan_s.toString(),time_s.toString())
        callUpdatedata.enqueue(object: Callback<responseModel> {
            override fun onFailure(call: Call<responseModel>, t: Throwable) {
                Toast.makeText(this@AddNoteActivity,"failure"+id,Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:",""+t.message);
                finish()
            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@AddNoteActivity,"succes"+id,Toast.LENGTH_SHORT).show()
                val response = response.body() as responseModel
                System.out.println("test : "+response.message)

                //val resultIntent = Intent()
                //setResult(Activity.RESULT_OK,resultIntent)
                finish()


            }

        })

    }

    fun deleteData(id: Int){
        val deleteData = RetrofitBuilder.builder(this!!.applicationContext).create(getDataWalletInterface::class.java)
        val calldelete = deleteData.DeleteDataWallet(id.toString())
        calldelete.enqueue(object : Callback<responseModel>{
            override fun onFailure(call: Call<responseModel>, t: Throwable) {

            }

            override fun onResponse(call: Call<responseModel>, response: Response<responseModel>) {
                Toast.makeText(this@AddNoteActivity,"berhasil delete",Toast.LENGTH_SHORT).show()
                finish()
            }
        })

    }
}
