package com.mazur.caloriediary.ui.meals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mazur.caloriediary.R
import com.mazur.caloriediary.ui.BaseFragment

class MealsFragment : BaseFragment(), MealsView {
    private lateinit var presenter: MealsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_meals, container, false)

        presenter = ViewModelProviders.of(this).get(MealsPresenter::class.java)
        presenter.view = this

        return view
    }
}