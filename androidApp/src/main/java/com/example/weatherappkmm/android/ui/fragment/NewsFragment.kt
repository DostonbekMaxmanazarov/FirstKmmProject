package com.example.weatherappkmm.android.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherappkmm.android.ui.adapter.NewsAdapter
import com.example.weatherappkmm.android.databinding.FragmentNewsBinding
import com.example.weatherappkmm.mvp.presentation.INewsPresenter
import com.example.weatherappkmm.mvp.presentation.NewsPresenterImpl
import com.example.weatherappkmm.mvp.view.INewsView
import com.example.weatherappkmm.network.response.news.NewsDataResponse

class NewsFragment : Fragment(), INewsView {

    private lateinit var binding: FragmentNewsBinding

    private lateinit var adapter: NewsAdapter

    private var presenter: INewsPresenter? = null

    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initRefresh()
        updateHandler()
    }

    override fun onResume() {
        super.onResume()
        handler.removeCallbacks(runnable)
        handler.postDelayed(runnable,5_000)
    }

    override fun onPause() {
        super.onPause()
        presenter?.cancel()
        handler.removeCallbacks(runnable)
    }

    override fun sendNewsData(newsDataResponse: NewsDataResponse) {
        newsDataResponse.articles?.let { adapter.setData(it) }
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

    private fun initRefresh(){
        binding.refresh.isRefreshing=true
        binding.refresh.setOnRefreshListener {
            presenter?.loadNews("us", "business")
        }
    }

    private fun initView() {
        adapter = NewsAdapter()
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        presenter = NewsPresenterImpl().attachView(this) as NewsPresenterImpl
        presenter?.loadNews("us", "business")
    }

    private fun updateHandler() {
        handler = Handler(Looper.getMainLooper())

        runnable = object : Runnable {
            override fun run() {
                binding.refresh.isRefreshing = true
                handler.postDelayed(this, 5_000)
                binding.refresh.isRefreshing = false
                presenter?.loadNews("us", "business")
                Log.d("Runnable","Run Weather")

            }
        }

        runnable.run()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.cancel()
    }
}