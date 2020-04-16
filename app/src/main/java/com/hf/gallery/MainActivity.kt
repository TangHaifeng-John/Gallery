package com.hf.gallery


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

import com.hf.banner.gallery.GalleryLayoutManager
import com.hf.banner.gallery.ScaleTransformer
import com.jm.android.kadapter.KAdapterFactory
import com.jm.android.kadapter.KotlinAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_banner.view.*

class MainActivity : AppCompatActivity() {
    var layoutManager: GalleryLayoutManager?=null

    var testListAdapter: KotlinAdapter<String> = KAdapterFactory.KAdapter {

        layout {
            R.layout.item_banner
        }




        bindData { type, vh, data ->
            when (type) {
                R.layout.item_banner -> {
                    Glide.with(this@MainActivity).load(data).into(vh.itemView.image)
                }


            }


        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initPages()

    }

    private fun initPages() {

        banner.adapter=testListAdapter
        indicator.attachToRecyclerView(banner)
        layoutManager = GalleryLayoutManager(GalleryLayoutManager.HORIZONTAL)

        layoutManager?.attach(banner, 0)
        layoutManager?.setItemTransformer(ScaleTransformer())
        layoutManager?.setCallbackInFling(false)
        testListAdapter?.registerAdapterDataObserver(indicator.adapterDataObserver)

        testListAdapter.data{
            arrayListOf(
                    "https://tva3.sinaimg.cn/large/0072Vf1pgy1foxkg6fz2pj31hc0u0auw.jpg",
                    "https://tva4.sinaimg.cn/large/0072Vf1pgy1foxk3mu0ztj31hc0u07m5.jpg",
                    "https://tva1.sinaimg.cn/large/0072Vf1pgy1foxk7r8ic6j31hc0u0k7b.jpg",
                    "https://tva2.sinaimg.cn/large/87c01ec7gy1frmmu5g157j21hc0u0x6q.jpg"
            )
        }
        testListAdapter.notifyDataSetChanged()

    }
}
