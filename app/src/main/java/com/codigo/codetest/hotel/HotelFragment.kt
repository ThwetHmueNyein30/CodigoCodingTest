package com.codigo.codetest.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aemerse.slider.listener.CarouselListener
import com.aemerse.slider.listener.CarouselOnScrollListener
import com.aemerse.slider.model.CarouselItem
import com.aemerse.slider.utils.setImage
import com.codigo.codetest.R
import com.codigo.codetest.databinding.FragmentHotelBinding
import com.codigo.codetest.databinding.ItemSliderBinding
import com.google.android.material.tabs.TabLayoutMediator

class HotelFragment : Fragment() {

    private lateinit var binding: FragmentHotelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBanner()
        setupRoomAndRate()
        actionListener()
    }

    private fun actionListener() {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setupRoomAndRate() {
        val pages = listOf(
            Pair(getString(R.string.by_room), RoomsFragment()),
            Pair(getString(R.string.by_rate), RatesFragment())
        )

        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = pages.size
            override fun createFragment(position: Int): Fragment = pages[position].second
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = pages[position].first
        }.attach()
    }

    private fun setupBanner() {
        val list = mutableListOf<CarouselItem>(
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
            ),
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
            ),
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
            ),
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
            ),
            CarouselItem(
                imageUrl = "https://images.unsplash.com/photo-1534447677768-be436bb09401?w=1080"
            ),
        )
        binding.carousel.apply {
            registerLifecycle(lifecycle = lifecycle)
            carouselListener = object : CarouselListener {
                override fun onCreateViewHolder(
                    layoutInflater: LayoutInflater,
                    parent: ViewGroup
                ): ViewBinding {
                    return ItemSliderBinding.inflate(layoutInflater, parent, false)
                }

                override fun onBindViewHolder(
                    binding: ViewBinding,
                    item: CarouselItem,
                    position: Int
                ) {
                    val currentBinding = binding as ItemSliderBinding

                    currentBinding.imageView.apply {
                        scaleType = imageScaleType
                        setImage(item)
                    }
                }
            }
            onScrollListener = object : CarouselOnScrollListener {

                override fun onScrollStateChanged(
                    recyclerView: RecyclerView,
                    newState: Int,
                    position: Int,
                    carouselItem: CarouselItem?
                ) {

                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        carouselItem?.apply {
                            binding.sliderIndex.text = "${list.indexOf(carouselItem)+1}/${list.size}"
                        }
                    }
                }

                override fun onScrolled(
                    recyclerView: RecyclerView,
                    dx: Int,
                    dy: Int,
                    position: Int,
                    carouselItem: CarouselItem?
                ) {}
            }
            setData(list)
        }
    }
}