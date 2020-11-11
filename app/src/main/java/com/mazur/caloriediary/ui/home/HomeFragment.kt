package com.mazur.caloriediary.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.navigation.NavigationView
import com.mazur.caloriediary.R
import com.mazur.caloriediary.ui.BaseFragment
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_wrapper.*
import kotlinx.android.synthetic.main.fragment_home.view.*

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
//                drawer.nav_head_appname.text = "email"
            } else {
                drawer.closeDrawer(GravityCompat.END)
            }
        }

        return view
    }

//    override fun logout() {
//        Hawk.put(Preferences.LOGGED, false)
//        val intent = Intent(context, LoginActivity::class.java)
//        startActivity(intent)
//        activity?.finish()
//    }

}