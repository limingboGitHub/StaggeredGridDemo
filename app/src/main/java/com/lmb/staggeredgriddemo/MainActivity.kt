package com.lmb.staggeredgriddemo

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = listOf(1,2,3,4,5,6,7,8,9,10
        ,11,12,13,14,15,16,17,18,19,20)
        val adapter = MyAdapter()

        recycler_view.layoutManager = StaggeredGridLayoutManager(2,RecyclerView.VERTICAL)
        recycler_view.adapter = adapter
        adapter.setList(data)
    }

    inner class MyAdapter : BaseQuickAdapter<Int,BaseViewHolder>(R.layout.adapter_item){
        override fun convert(holder: BaseViewHolder, item: Int) {
            //图片
            val image_view = holder.getView<ImageView>(R.id.image_view)
            when {
                item%3 == 0 -> {
                    setImageView(image_view,R.mipmap.test1)
                }
                item%3 == 1 -> {
                    setImageView(image_view,R.mipmap.test2)
                }
                else -> {
                    setImageView(image_view,R.mipmap.test3)
                }
            }
            //标题
            val stringBuilder = StringBuilder()
            for (i in 0..item)
                stringBuilder.append("时空印")
            holder.setText(R.id.image_title_tv,stringBuilder)
            //用户名
            holder.setText(R.id.username_tv,item.toString())
        }
    }

    private fun setImageView(image_view: ImageView, imageResId: Int) {
        val optional = BitmapFactory.Options()
        optional.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources,imageResId,optional)
        //动态确定imageView的宽高比
        val ratio = optional.outHeight / optional.outWidth.toFloat()
        val ratioString = "h,1:$ratio"
        val layoutParams = image_view.layoutParams as ConstraintLayout.LayoutParams
        layoutParams.dimensionRatio = ratioString
        image_view.layoutParams = layoutParams

        image_view.setImageResource(imageResId)
    }
}