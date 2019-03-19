package com.company107.myapplication.UI.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.company107.myapplication.Data.Adapter.NotesAdapter
import com.company107.myapplication.Data.Model.Data
import com.company107.myapplication.Data.Model.Notes
import com.company107.myapplication.Data.Network.RetrofitBuilder
import com.company107.myapplication.Data.Network.getDataWalletInterface
import com.company107.myapplication.Data.ViewModel.DasboardViewModel
import com.company107.myapplication.R
import com.company107.myapplication.UI.AddNoteActivity
import kotlinx.android.synthetic.main.dasboard_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DasboardFragment : Fragment() {

    companion object {
        fun newInstance() = DasboardFragment()
    }

    private lateinit var viewModel: DasboardViewModel
    private val REQUEST_SEND = 1
    private val REQUEST_RECEIVE = 2

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: NotesAdapter

    private var newsList: ArrayList<Data>? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dasboard_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DasboardViewModel::class.java)
        // TODO: Use the ViewModel


        card_view_send.setOnClickListener {
            val intent = Intent(activity, AddNoteActivity::class.java)
            intent.putExtra("request_type", "send")
            intent.putExtra("add_or_edit", "add")
            startActivity(intent)
        }

        card_view_receive.setOnClickListener {
            val intent = Intent(activity, AddNoteActivity::class.java)
            intent.putExtra("request_type", "receive")
            intent.putExtra("add_or_edit", "add")
            startActivity(intent)
        }


        val getdatawallet =
            RetrofitBuilder.builder(activity!!.applicationContext).create(getDataWalletInterface::class.java)
        val callgetdata = getdatawallet.GetDataWallet()
        callgetdata.enqueue(object : Callback<Notes> {
            override fun onFailure(call: Call<Notes>, t: Throwable) {
                Toast.makeText(activity, "failure", Toast.LENGTH_SHORT).show()
                Log.i("dasboardfragmetn:", "" + t.message);
            }

            override fun onResponse(call: Call<Notes>, response: Response<Notes>) {
                val response = response.body() as Notes
                val size = response.data!!.size
                val data = response.data!!

                if (data.isEmpty()) {

                } else {
                    mAdapter = NotesAdapter(data, activity!!)
                    linearLayoutManager = LinearLayoutManager(activity)

                    recycleview.layoutManager = linearLayoutManager
                    recycleview.setHasFixedSize(true)
                    recycleview.adapter = mAdapter


                    mAdapter.setOnItemClickListener(object : NotesAdapter.OnItemClickListener {
                        override fun onItemClick(data: Data) {
                            //Toast.makeText(activity,""+data.id,Toast.LENGTH_SHORT).show()
                            var intent = Intent(activity, AddNoteActivity::class.java)
                            intent.putExtra("id", data.id?.toInt())
                            intent.putExtra("request_type", data.jenis_pengeluaran?.toString())
                            intent.putExtra("jumlah_uang", data.jumlah_uang?.toString())
                            intent.putExtra("penggunaan", data.penggunaan?.toString())
                            intent.putExtra("sumber_uang", data.sumber_uang?.toString())
                            intent.putExtra("time", data.time?.toString())
                            intent.putExtra("add_or_edit", "edit")
                            startActivity(intent)

                        }
                    })


                }
//                for (i in 0 until size){
//                    val jenis_pengeluaran = response.data!!.get(i).jenis_pengeluaran
//                    System.out.println("test : "+jenis_pengeluaran)
////                    Toast.makeText(activity,""+jenis_pengeluaran,Toast.LENGTH_SHORT)
//                }

            }

        })


    }


}
