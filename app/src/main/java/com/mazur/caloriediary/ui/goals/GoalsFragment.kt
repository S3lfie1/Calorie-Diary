package com.mazur.caloriediary.ui.goals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mazur.caloriediary.R
import com.mazur.caloriediary.ui.BaseFragment

class GoalsFragment : BaseFragment(), GoalsView {
    private lateinit var presenter: GoalsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_goals, container, false)

        presenter = ViewModelProviders.of(this).get(GoalsPresenter::class.java)
        presenter.view = this

        return view
    }
}