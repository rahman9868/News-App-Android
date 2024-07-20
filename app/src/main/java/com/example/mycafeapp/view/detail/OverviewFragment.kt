package com.example.mycafeapp.view.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycafeapp.R
import com.example.mycafeapp.entity.Cafe
import com.example.mycafeapp.util.DummyUtil
import kotlinx.android.synthetic.main.fragment_overview.txtLocation
import kotlinx.android.synthetic.main.fragment_overview.txtRate
import kotlinx.android.synthetic.main.fragment_overview.txtTime
import kotlinx.android.synthetic.main.fragment_overview.txtType

private const val ARG_PARAM1 = "param1"

class OverviewFragment : Fragment() {
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDetailCafe()
    }

    @SuppressLint("SetTextI18n")
    private fun getDetailCafe() {
        id?.let {
            val detail = DummyUtil.getDetailCafeById(it)
            detail?.let { cafe ->
                txtType.text = cafe.type
                txtTime.text = cafe.openTime +" - "+ cafe.closeTime
                txtLocation.text = cafe.address
                txtRate.text = cafe.rate.toString()
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            OverviewFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, id)
                }
            }
    }
}