package com.mazur.caloriediary.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mazur.caloriediary.R
import com.mazur.caloriediary.ui.BaseFragment

class StatsFragment : BaseFragment(), StatsView {
    private lateinit var presenter: StatsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        presenter = ViewModelProviders.of(this).get(StatsPresenter::class.java)
        presenter.view = this

        return view
    }
}