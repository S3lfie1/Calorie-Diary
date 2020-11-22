package com.mazur.caloriediary.ui.meals

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
import com.mazur.caloriediary.ui.meals.meal_form.MealFormFragment
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_meals.view.*
import java.util.*
import kotlin.collections.ArrayList

class MealsFragment : BaseFragment(), MealsView {
    private lateinit var presenter: MealsPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meals, container, false)

        presenter = ViewModelProviders.of(this).get(MealsPresenter::class.java)
        presenter.view = this

        val meals = listOf(view.meal1, view.meal2, view.meal3, view.meal4, view.meal5, view.meal6)

        meals.forEachIndexed { index, button ->
            val allMeals: ArrayList<Meal>? = Hawk.get(Preferences.USER_MEALS)
            if (!allMeals.isNullOrEmpty()) {
                allMeals.forEach { it ->
                    if (it.kind == index && DateUtils.isToday(it.date.time)) {
                        button.isEnabled = false
                        button.setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
                    }
                }
            }

            button.setOnClickListener { this.addMeal(index) }
        }


        return view
    }

    override fun addMeal(id: Int) {
        val bundle = Bundle()
        bundle.putSerializable("meal", id)
        val mealFormFragment = MealFormFragment()
        mealFormFragment.arguments = bundle
        navigator?.navigateTo(mealFormFragment, true)
    }
}