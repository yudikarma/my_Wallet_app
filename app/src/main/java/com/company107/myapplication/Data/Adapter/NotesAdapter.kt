package com.company107.myapplication.Data.Adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.company107.myapplication.Data.Model.Data
import com.company107.myapplication.Data.Model.Notes
import com.company107.myapplication.R
import kotlinx.android.synthetic.main.item_pengeluaran.view.*
import android.view.LayoutInflater
import android.widget.Toast



class NotesAdapter(private val notes: ArrayList<Data> ,context: Context) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {
    private var context:Context? = context
    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_pengeluaran, parent, false)
        return NotesViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
       val itemnotes = notes[position]
        holder.bindView(itemnotes)

    }

    inner class NotesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        private var data:Data? = null

         fun onClick(position: Int) {
           Toast.makeText(context,""+position,Toast.LENGTH_SHORT).show()
        }

        init {
            itemView.setOnClickListener(){
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION){
                    listener?.onItemClick(notes[position])
                }
            }
        }

        fun bindView(data: Data){
            this.data = data
            itemView.jenis_pengeluaran.text = this.data?.jenis_pengeluaran
            itemView.penggunaan.text = this.data?.penggunaan
            itemView.time.text = this.data?.time
            itemView.jumlah_uang.text = this.data?.jumlah_uang
        }



    }

    interface OnItemClickListener {
        fun onItemClick(data: Data)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}