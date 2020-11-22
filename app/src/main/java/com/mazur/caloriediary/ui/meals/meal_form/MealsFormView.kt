package com.mazur.caloriediary.ui.meals.meal_form

import android.view.View
import com.mazur.caloriediary.model.Meal

interface MealsFormView {
    fun saveMeal(meal: ArrayList<Meal>)
    fun getMeal()
    fun setTitle(view: View)
}