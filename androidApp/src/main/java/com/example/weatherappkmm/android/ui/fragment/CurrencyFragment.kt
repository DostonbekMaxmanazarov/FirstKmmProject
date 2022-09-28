package com.example.weatherappkmm.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.weatherappkmm.android.App
import com.example.weatherappkmm.android.R
import com.example.weatherappkmm.android.databinding.FragmentCurrencyBinding
import com.example.weatherappkmm.mvp.presentation.CurrencyPresenterImpl
import com.example.weatherappkmm.mvp.presentation.ICurrencyPresenter
import com.example.weatherappkmm.mvp.view.ICurrencyView
import com.example.weatherappkmm.network.response.currency.CurrencyDataResponse
import kotlinx.coroutines.launch

class CurrencyFragment : Fragment(), ICurrencyView {

    private lateinit var binding: FragmentCurrencyBinding

    private var presenter: ICurrencyPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrencyBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = CurrencyPresenterImpl().attachView(this) as CurrencyPresenterImpl
        initClickView()

    }


    override fun sendCurrencyData(currencyDataResponse: CurrencyDataResponse) {
        binding.tvResult.text =
            binding.etFrom.text.toString() + " " +
                    binding.spFromCurrency.selectedItem.toString() + " " +
                    currencyDataResponse.result.toString() + " " +
                    binding.spToCurrency.selectedItem.toString()

        lifecycleScope.launch {
            currencyDataResponse.result?.toString()?.let { App.database?.add(it) }
        }

        binding.progressBar.hide()
    }


    override fun showLoading(show: Boolean) {
        if (!show) {
            binding.progressBar.hide()
        }
    }


    override fun showError(error: Throwable) {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Fail")
            .setMessage(error.message)
            .create()

        alertDialog.show()
    }


    override fun showErrorMessage(message: String) {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Fail")
            .setMessage(message)
            .create()

        alertDialog.show()
    }


    private fun initClickView() {
        binding
            .btnConvert
            .setOnClickListener {

                presenter?.loadCurrency(
                    binding.spFromCurrency.selectedItem.toString(),
                    binding.spToCurrency.selectedItem.toString(),
                    binding.etFrom.text.toString()
                )

                binding.progressBar.show()
            }

        binding
            .ivSave
            .setOnClickListener {
                val fragment= SaveCurrencyFragment()

                val fragmentTransaction = parentFragmentManager
                    .beginTransaction()
                    .addToBackStack(fragment.javaClass.simpleName)
                    .replace(R.id.fragment_container, fragment)

                fragmentTransaction.commitAllowingStateLoss()
            }
    }
}