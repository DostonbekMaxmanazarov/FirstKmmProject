package com.example.weatherappkmm.android.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.weatherappkmm.android.R
import com.example.weatherappkmm.android.ui.adapter.SpinnerAdapter
import com.example.weatherappkmm.android.databinding.FragmentWeatherBinding
import com.example.weatherappkmm.mvp.presentation.IWeatherPresenter
import com.example.weatherappkmm.mvp.presentation.WeatherMainPresenterImpl
import com.example.weatherappkmm.mvp.view.IWeatherMainView
import com.example.weatherappkmm.network.response.weather.WeatherDataResponse
import kotlin.math.log

class WeatherFragment : Fragment(), IWeatherMainView {

    private lateinit var binding: FragmentWeatherBinding

    private var adapterSpinner: SpinnerAdapter? = null

    private var presenter: IWeatherPresenter? = null

    private var countryName: String = "Uzbekistan"

    private var top: Animation? = null
    private var left: Animation? = null
    private var right: Animation? = null

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = WeatherMainPresenterImpl().attachView(this) as WeatherMainPresenterImpl

        loadAnimation()
        initView()
        updateHandler()
    }


    override fun onResume() {
        super.onResume()
        binding.refresh.isRefreshing=false
        presenter?.loadWeather(countryName)
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable,5_000)
    }


    override fun onPause() {
        super.onPause()
        presenter?.cancel()
        handler.removeCallbacks(runnable)

    }


    override fun sendWeatherData(weatherDataResponse: WeatherDataResponse) {

        binding.temp.text =
            weatherDataResponse.main?.temp?.minus(273.15)?.toString()?.substring(0, 5)

        binding.maxTemp.text =
            weatherDataResponse.main?.temp_max?.minus(273.15)?.toString()?.substring(0, 5)

        binding.minTemp.text =
            weatherDataResponse.main?.temp_min?.minus(273.15)?.toString()?.substring(0, 5)


        binding.mainText.text =
            weatherDataResponse.weather?.get(0)?.descriptionTemp

    }


    override fun showLoading(show: Boolean) {
        if (!show)
            binding.refresh.isRefreshing = false
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


    private fun initView() {
        binding.refresh.setOnRefreshListener {
            presenter?.loadWeather(countryName)
        }
        adapterSpinner = SpinnerAdapter(loadData())
        binding.spinner.adapter = adapterSpinner
        binding.spinner.onItemSelectedListener = selectedItem

    }


    private val selectedItem = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            countryName = parent?.getItemAtPosition(position).toString()
            presenter?.loadWeather(countryName)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            Toast.makeText(requireContext(), "No select", Toast.LENGTH_LONG).show()
        }
    }


    private fun loadAnimation() {
        top = AnimationUtils.loadAnimation(requireContext(), R.anim.topsun)
        left = AnimationUtils.loadAnimation(requireContext(), R.anim.left)
        right = AnimationUtils.loadAnimation(requireContext(), R.anim.right)

        binding.temp.animation = left
        binding.gradus.animation = left
        binding.mainText.animation = right
    }


    private fun updateHandler() {
        handler = Handler(Looper.getMainLooper())

        runnable = object : Runnable {
            override fun run() {
                binding.refresh.isRefreshing = true
                handler.postDelayed(this, 5_000)
                presenter?.loadWeather(countryName)
                binding.refresh.isRefreshing = false

            }
        }

        runnable.run()
    }


    private fun loadData(): List<String> {
        val data = ArrayList<String>()
        data.add("Uzbekistan")
        data.add("Tashkent")
        data.add("Jizzakh")
        data.add("Navoiy")
        data.add("Samarkand")
        data.add("Bukhara")
        data.add("Qarshi")
        data.add("Shahrisabz")
        data.add("Namangan")
        data.add("Andijan")
        data.add("Kokand")
        return data
    }
}