package com.mazur.caloriediary.ui.options

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mazur.caloriediary.R
import com.mazur.caloriediary.ui.BaseFragment

class OptionsFragment : BaseFragment(), OptionsView {
    private lateinit var presenter: OptionsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_options, container, false)

        presenter = ViewModelProviders.of(this).get(OptionsPresenter::class.java)
        presenter.view = this

        return view
    }
}