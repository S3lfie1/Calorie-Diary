package com.mazur.caloriediary.ui.home

import android.graphics.Color
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import com.hookedonplay.decoviewlib.DecoView
import com.hookedonplay.decoviewlib.charts.SeriesItem
import com.hookedonplay.decoviewlib.events.DecoEvent
import com.mazur.caloriediary.R
import com.mazur.caloriediary.helpers.Preferences
import com.mazur.caloriediary.model.Meal
import com.mazur.caloriediary.model.Option
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomePresenter : ViewModel() {
    var view: HomeView? = null

    fun initProgressBar(view: View) {
        var value = 0f
        var calories: Double = 0.0
        val decoView: DecoView = view.findViewById(R.id.dynamicArcView)
        val meals: ArrayList<Meal>? = Hawk.get(Preferences.USER_MEALS)
        if (!meals.isNullOrEmpty()) {
            meals.forEach {
                if (DateUtils.isToday(it.date.time))
                    calories+= it.calories
            }

            var minCalories = 2000
            val options: ArrayList<Option>? = Hawk.get(Preferences.USER_OPTIONS)
            val singleOption = options?.find { it.code == 1 }
            if (singleOption != null) {
                minCalories = singleOption.intValue!!
            }
            value = (calories.toFloat() / minCalories) * 100
        }

        decoView.addSeries(
            SeriesItem.Builder(Color.parseColor("#6200EE"))
            .setRange(0f, 100f, 100f)
            .setInitialVisibility(true)
            .setLineWidth(35f)
            .build())

//        val googleFit = decoView.addSeries(SeriesItem.Builder(Color.parseColor("#f8b656"))
//                .setRange(0f, 100f, 30f)
//                .setInset(PointF(20f, 20f))
//                .setLineWidth(10f)
//                .setSeriesLabel(SeriesLabel.Builder("Condition %.0f%%")
//                        .setFontSize(10f)
//                        .setColorBack(Color.argb(218, 0, 0, 0))
//                        .setColorText(Color.argb(255, 255, 255, 255))
//                        .build())
//                .build())

        val healthStatus = decoView.addSeries(
            SeriesItem.Builder(Color.parseColor("#FFFFFF"))
            .setCapRounded(false)
            .setRange(0f, 100f, 0f)
            .setLineWidth(10f)
            .build())

        view.textPercentage.text = String.format("%.0f%%", value)
        decoView.addEvent(DecoEvent.Builder(value).setIndex(healthStatus).build())
//        decoView.addEvent(DecoEvent.Builder(value).setIndex(googleFit).build())
        decoView.configureAngles(360, 0)
    }
}