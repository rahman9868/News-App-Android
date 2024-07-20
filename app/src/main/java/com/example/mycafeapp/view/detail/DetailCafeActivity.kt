package com.example.mycafeapp.view.detail


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mycafeapp.R
import com.example.mycafeapp.entity.Cafe
import com.example.mycafeapp.util.DummyUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_detail_cafe.ivPictCafe
import kotlinx.android.synthetic.main.activity_detail_cafe.txtName

class DetailCafeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_cafe)
        getDetailCafe()
    }

    private fun getDetailCafe() {
        val id = intent?.getIntExtra(DETAIL_KEY, 0)
        id?.let {
            val detail = DummyUtil.getDetailCafeById(it)
            detail?.let { cafe ->
                txtName.text = cafe.name
                ivPictCafe.setImageResource(cafe.picture)
                sectionDetail(cafe)
            }
        }
    }

    private fun sectionDetail(cafe: Cafe) {
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        val adapter = SectionsPagerAdapter(this, cafe)

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()

    }

    inner class SectionsPagerAdapter(fragmentActivity: FragmentActivity, val cafe: Cafe) : FragmentStateAdapter(fragmentActivity) {
        private val tabTitles = arrayOf("Overview", "Menu", "Reviews")

        override fun getItemCount(): Int = tabTitles.size

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> OverviewFragment.newInstance(cafe.id)
                1 -> MenuFragment.newInstance(cafe.id)
                2 -> ReviewFragment.newInstance(cafe.id)
                else -> OverviewFragment.newInstance(cafe.id)
            }
        }

        fun getTabTitle(position: Int): String {
            return tabTitles[position]
        }
    }

    companion object{
        val DETAIL_KEY = "Detail Key"
    }
}