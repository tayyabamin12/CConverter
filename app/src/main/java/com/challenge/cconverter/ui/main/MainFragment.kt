package com.challenge.cconverter.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import com.challenge.cconverter.data.api.ApiHelper
import com.challenge.cconverter.data.api.ApiServiceImpl
import com.challenge.cconverter.data.model.Quotes
import com.challenge.cconverter.data.utils.CommonUtils
import com.challenge.cconverter.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mainViewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModel()
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupDocsObserver()
        mainViewModel.getRecord(requireContext())

        setRecyclerView()
        return root
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(this,
            ViewModelFactory(ApiHelper(ApiServiceImpl())))
                .get(MainViewModel::class.java)
    }

    private fun setupDocsObserver() {
        mainViewModel.getCurrencyResponse().observe(viewLifecycleOwner, {
            if (it != null) {
                Log.d("defghi", it.toString())
                setSpinner(it.quotes)
            }
        })
    }

    private fun setSpinner(quotes: Quotes) {
        val spinner = binding.dowIndexText
        val currencyList = Array(CommonUtils.currencyList().size) {i -> CommonUtils.currencyList()[i] }

        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, currencyList)
        spinner.setAdapter(adapter)

        spinner.onItemClickListener =
            AdapterView.OnItemClickListener { p0, p1, p2, p3 ->
                getAllCurrencyRecord(currencyList[p2])
            }
    }

    private fun getAllCurrencyRecord(s: String) {
        val value = 1.0
        mainViewModel.convertCurrencies(requireContext(), s, value)
    }

    private fun setRecyclerView() {
        binding.rvCurrencies.layoutManager = GridLayoutManager(context,3)
        val adapterView = context?.let { RecyclerAdapter(it) }
        binding.rvCurrencies.adapter = adapterView

        //add data
        val currencyList = Array(CommonUtils.currencyList().size) {i -> CommonUtils.currencyList()[i] }

        adapterView?.setDataList(currencyList)
    }
}