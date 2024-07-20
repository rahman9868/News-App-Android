package com.example.mycafeapp.view.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycafeapp.R
import com.example.mycafeapp.util.DummyUtil

private const val ARG_PARAM1 = "param1"

class MenuFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDetailCafe()
    }

    private fun getDetailCafe() {
        id?.let {
            val detail = DummyUtil.getDetailCafeById(it)
            detail?.let { cafe ->
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, id)
                }
            }
    }
}