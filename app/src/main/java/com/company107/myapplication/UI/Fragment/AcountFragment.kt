package com.company107.myapplication.UI.Fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.company107.myapplication.Data.ViewModel.AcountViewModel

import com.company107.myapplication.R

class AcountFragment : Fragment() {

    companion object {
        fun newInstance() = AcountFragment()
    }

    private lateinit var viewModel: AcountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.acount_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AcountViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
