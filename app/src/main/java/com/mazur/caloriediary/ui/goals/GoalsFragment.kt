package com.mazur.caloriediary.ui.goals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mazur.caloriediary.R
import com.mazur.caloriediary.helpers.Preferences
import com.mazur.caloriediary.model.Option
import com.mazur.caloriediary.ui.BaseFragment
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_goals.view.*

class GoalsFragment : BaseFragment(), GoalsView {
    private lateinit var presenter: GoalsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_goals, container, false)

        presenter = ViewModelProviders.of(this).get(GoalsPresenter::class.java)
        presenter.view = this

        val options : ArrayList<Option> = Hawk.get(Preferences.USER_OPTIONS)
        if (!options.isNullOrEmpty()) {
            val optionFirst = options.find { it.code == 1 }
            view.min_calories.setText(optionFirst?.intValue.toString())

            val optionSecond = options.find { it.code == 2 }
            view.max_calories.setText(optionSecond?.intValue.toString())
        }

        view.saveOptions.setOnClickListener {
            val options : ArrayList<Option> = arrayListOf()
            options.add(Option(1, view.min_calories.text.toString().toInt(), null))
            options.add(Option(2, view.max_calories.text.toString().toInt(), null))
            Hawk.put(Preferences.USER_OPTIONS, options)
        }

        return view
    }
}