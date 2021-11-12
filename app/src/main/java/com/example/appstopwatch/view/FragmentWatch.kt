package com.gb.stopwatch.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.gb.stopwatch.viewmodel.ViewModelStopwatch
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.appstopwatch.R
import com.example.appstopwatch.databinding.FragmentWatchBinding
import com.gb.stopwatch.model.formatter.TimestampMillisecondsFormatter
import com.gb.stopwatch.model.states.AppState
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentWatch : Fragment(R.layout.fragment_watch), ViewStopwatch {

    private val viewBinding: FragmentWatchBinding by viewBinding()
    private val viewModelWatch: ViewModelStopwatch by viewModel()
    private var buttonStartFlag = true;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        viewModelWatch.watch()

        viewModelWatch.getWatch().observe(viewLifecycleOwner, Observer {
            renderData(it, viewBinding.textTimeWatch)
        })
    }

    override fun renderData(appState: AppState<Long>, textView: TextView) {
        when (appState) {
            is AppState.Error -> {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
            is AppState.Success -> {
                textView.text =
                    TimestampMillisecondsFormatter().format(appState.time)
            }
        }
    }

    override fun initButtons() {

        with(viewBinding) {
            buttonStartWatch.setOnClickListener {
                if (buttonStartFlag) {
                    viewBinding.buttonStartWatch.text = getString(R.string.pause)
                    buttonStartFlag = !buttonStartFlag
                    viewModelWatch.start()
                } else {
                    viewBinding.buttonStartWatch.text = getString(R.string.start)
                    buttonStartFlag = !buttonStartFlag
                    viewModelWatch.pause()
                }
            }
            buttonStopWatch.setOnClickListener {
                viewBinding.buttonStartWatch.text = getString(R.string.start)
                buttonStartFlag = true
                viewModelWatch.stop()
            }
        }
    }
}