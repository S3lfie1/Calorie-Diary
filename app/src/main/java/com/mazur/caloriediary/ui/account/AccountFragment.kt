package com.mazur.caloriediary.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.mazur.caloriediary.R
import com.mazur.caloriediary.ui.BaseFragment

class AccountFragment : BaseFragment(), AccountView {
    private lateinit var presenter: AccountPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_account, container, false)

        presenter = ViewModelProviders.of(this).get(AccountPresenter::class.java)
        presenter.view = this

        return view
    }
}