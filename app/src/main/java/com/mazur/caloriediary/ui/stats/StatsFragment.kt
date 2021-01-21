package com.mazur.caloriediary.ui.stats

import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mazur.caloriediary.R
import com.mazur.caloriediary.helpers.Preferences
import com.mazur.caloriediary.model.Meal
import com.mazur.caloriediary.ui.BaseFragment
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_stats.view.*
import java.util.*
import kotlin.collections.ArrayList

class StatsFragment : BaseFragment(), StatsView {
    private lateinit var presenter: StatsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stats, container, false)

        presenter = ViewModelProviders.of(this).get(StatsPresenter::class.java)
        presenter.view = this

        var calories = 0.0
        val meals: ArrayList<Meal>? = Hawk.get(Preferences.USER_MEALS)

        meals?.forEach { it ->
            if (DateUtils.isToday(it.date.time))
                calories += it.calories
        }

        view.calories.text = calories.toString()
        return view
    }
}