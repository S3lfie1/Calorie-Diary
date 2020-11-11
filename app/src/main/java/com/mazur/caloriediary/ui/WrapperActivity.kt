package com.mazur.caloriediary.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.mazur.caloriediary.R
import com.mazur.caloriediary.ui.account.AccountFragment
import com.mazur.caloriediary.ui.goals.GoalsFragment
import com.mazur.caloriediary.ui.home.HomeFragment
import com.mazur.caloriediary.ui.meals.MealsFragment
import com.mazur.caloriediary.ui.options.OptionsFragment
import com.mazur.caloriediary.ui.stats.StatsFragment
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_wrapper.*

class WrapperActivity : AppCompatActivity(), Navigator {

    private var menuTransactionId: Int? = null
    private val transactionTag = "transaction_id"

    companion object {
        private const val MENU_HOME = 1
        private const val MENU_MEALS = 2
        private const val MENU_GOALS = 3
        private const val MENU_STATS = 4
        private const val MENU_ACCOUNT = 5
        private const val MENU_OPTIONS = 6
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrapper)

        menuTransactionId = savedInstanceState?.getInt(transactionTag)

        Toasty.Config.getInstance()
            .allowQueue(false)
            .apply()

        loadBottomMenu()

        if (savedInstanceState != null) {
            return
        } else {
            supportFragmentManager.beginTransaction().add(R.id.container, HomeFragment()).commit()
        }

    }

    private fun loadBottomMenu() {
        val bottomNavigation: MeowBottomNavigation = bottom_navigation as MeowBottomNavigation
        bottomNavigation.add(MeowBottomNavigation.Model(MENU_HOME, R.drawable.ic_home_black_24dp))
        bottomNavigation.add(MeowBottomNavigation.Model(MENU_MEALS, R.drawable.ic_baseline_restaurant_24))
        bottomNavigation.add(MeowBottomNavigation.Model(MENU_GOALS, R.drawable.ic_baseline_collections_bookmark_24))
        bottomNavigation.add(MeowBottomNavigation.Model(MENU_STATS, R.drawable.ic_baseline_timeline_24))
        bottomNavigation.add(MeowBottomNavigation.Model(MENU_ACCOUNT, R.drawable.ic_baseline_account_circle_24))
        bottomNavigation.add(MeowBottomNavigation.Model(MENU_OPTIONS, R.drawable.ic_baseline_settings_24))
        bottomNavigation.show(MENU_HOME)
        bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                MENU_HOME -> navigateTo(HomeFragment())
                MENU_MEALS -> navigateTo(MealsFragment())
                MENU_GOALS -> navigateTo(GoalsFragment())
                MENU_STATS -> navigateTo(StatsFragment())
                MENU_ACCOUNT -> navigateTo(AccountFragment())
                MENU_OPTIONS -> navigateTo(OptionsFragment())
                else -> Toast.makeText(this, "Something is wrong with menu :(", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            menuTransactionId?.let {
                putInt(transactionTag, it)
            }
        }
        super.onSaveInstanceState(outState)
    }

    @SuppressLint("PrivateResource")
    private fun replaceFragment(fragment: Fragment, addToBackStack: Boolean): Int {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)

        if (addToBackStack) {
            transaction.addToBackStack(null)
        }

        return transaction.commit()
    }

    override fun navigateTo(fragment: BaseFragment, addToBackStack: Boolean) {
        Handler().post {
            replaceFragment(fragment, addToBackStack)
        }
    }

    @SuppressLint("PrivateResource")
    override fun navigateTo(fragment: BaseFragment, target: BaseFragment) {
        Handler().post {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    override fun navigateBack() {
        supportFragmentManager.popBackStack()
    }

    override fun navigateToMenu() {
        menuTransactionId = replaceFragment(HomeFragment(), true)
    }

    override fun navigateBackToMenu() {
        menuTransactionId?.let {
            supportFragmentManager.popBackStack(it, 0)
        }
    }

}