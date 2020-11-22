package com.mazur.caloriediary.ui.meals.meal_form

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mazur.caloriediary.R
import com.mazur.caloriediary.helpers.Preferences
import com.mazur.caloriediary.model.Meal
import com.mazur.caloriediary.ui.BaseFragment
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_meal_form.view.*
import java.lang.Double
import java.util.*
import kotlin.collections.ArrayList

class MealFormFragment : BaseFragment(), MealsFormView {
    private lateinit var presenter: MealFormPresenter
    private var mealId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meal_form, container, false)

        presenter = ViewModelProviders.of(this).get(MealFormPresenter::class.java)
        presenter.view = this

        mealId = arguments?.getSerializable("meal") as Int

        this.setTitle(view)
        view.btn_savemeal.setOnClickListener {

            try {
                val kCal = Double.parseDouble(view.txt_mealkcal.text.toString())
                val title = view.txt_mealtitle.text.toString()

                if(kCal > 0 && title.isNotEmpty()) {
                    val meal = Meal(title, kCal, mealId, Date())
                    var allMeals = Hawk.get(Preferences.USER_MEALS) as ArrayList<Meal>?
                    if (allMeals.isNullOrEmpty()) {
                        allMeals = arrayListOf<Meal>()
                    }
                    allMeals.add(meal)

                    this.saveMeal(allMeals)

                }
            } catch (e: NumberFormatException) {}

        }
        val message = Hawk.get(Preferences.USER_MEALS) as ArrayList<Meal>?

        Log.i("LOG", message.toString())
        return view
    }

    override fun saveMeal(meal: ArrayList<Meal>) {
        Hawk.put(Preferences.USER_MEALS, meal)
    }

    override fun getMeal() {

    }

    override fun setTitle(view: View) {
        val title = listOf(R.string.breakfast, R.string.second_breakfast, R.string.midday_meal, R.string.dessert, R.string.afternoon_snack, R.string.supper)

        view.meal_title.setText("${view.meal_title.text} ${getString(title[mealId])}")
    }
}
