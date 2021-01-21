package com.mazur.caloriediary.ui.home

import android.graphics.Color
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import com.google.android.material.navigation.NavigationView
import com.mazur.caloriediary.R
import com.mazur.caloriediary.helpers.Preferences
import com.mazur.caloriediary.model.Meal
import com.mazur.caloriediary.ui.BaseFragment
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_wrapper.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_drawer_header.view.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : BaseFragment(), HomeView {
    private lateinit var presenter: HomePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        presenter = ViewModelProviders.of(this).get(HomePresenter::class.java)
        presenter.view = this

        val bottomNavigation: MeowBottomNavigation? = activity?.bottom_navigation
        bottomNavigation?.show(1)

        val navView: NavigationView = activity!!.nav_view
        val drawer: DrawerLayout = activity!!.drawer

        val compactCalendarView = view.compactcalendar_view as CompactCalendarView

        val events : ArrayList<Meal>? = Hawk.get(Preferences.USER_MEALS)
        events?.forEach {
            compactCalendarView.addEvent(Event(Color.GREEN, it.date.time))
        }

        compactCalendarView.setListener(object : CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {

            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
            }
        })

        navView.setNavigationItemSelectedListener(NavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
//                R.id.navigation_statistics -> showStatistics()
//                R.id.navigation_admin -> showOperatorPanel()
//                R.id.navigation_operator -> showOperatorPanel()
//                R.id.navigation_agreements -> showAgreements()
//                R.id.navigation_maps -> showMap()
//                R.id.navigation_logout -> logout()
            }

            drawer.closeDrawers()
            return@OnNavigationItemSelectedListener true
        })

        val menuBtn: Button = view.btn_home_menu
//        val email: String? = Hawk.get(Preferences.EMAIL)

        menuBtn.setOnClickListener {
            if (!drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.openDrawer(GravityCompat.START)
                drawer.nav_head_appname.text = "email"
            } else {
                drawer.closeDrawer(GravityCompat.END)
            }
        }

        this.setProgressBar(view)
        return view
    }

    override fun setProgressBar(view: View) {
        presenter.initProgressBar(view)
    }

//    override fun logout() {
//        Hawk.put(Preferences.LOGGED, false)
//        val intent = Intent(context, LoginActivity::class.java)
//        startActivity(intent)
//        activity?.finish()
//    }

}