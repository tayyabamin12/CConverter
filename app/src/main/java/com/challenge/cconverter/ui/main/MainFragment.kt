package com.challenge.cconverter.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.challenge.cconverter.data.utils.CommonUtils
import com.challenge.cconverter.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private var myCurrency: String = "AED"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setupViewModel()
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setSpinner()

        return root
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun setSpinner() {
        val currencySpinner = binding.currencySpinner
        val num = CommonUtils.currencyList()

        val adapter = ArrayAdapter(requireContext(),
            android.R.layout.simple_spinner_dropdown_item, num)
        currencySpinner.setAdapter(adapter)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        currencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                myCurrency = num[p2]
                Toast.makeText(requireContext(), num[p2], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
    }

}