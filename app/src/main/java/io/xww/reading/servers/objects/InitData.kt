package io.xww.reading.servers.objects

import io.xww.reading.data.Box

object InitData {
    private const val NUMBER = 100

    private val Img2t1 = arrayOf(
        "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3818275066,4114887538&fm=26&gp=0.jpg",
        "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3818275066,4114887538&fm=26&gp=0.jpg",
        "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3818275066,4114887538&fm=26&gp=0.jpg",
        "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3818275066,4114887538&fm=26&gp=0.jpg")

    //1:1图片
    private val Img1t1 = arrayOf(
        "https://img.alicdn.com/imgextra/i3/2104845057/TB2Lyp_g8USMeJjy1zkXXaWmpXa_!!2104845057.jpg",
        "https://img.alicdn.com/imgextra/i3/2104845057/TB2Lyp_g8USMeJjy1zkXXaWmpXa_!!2104845057.jpg",
        "https://img.alicdn.com/imgextra/i3/2104845057/TB2Lyp_g8USMeJjy1zkXXaWmpXa_!!2104845057.jpg",
        "https://img.alicdn.com/imgextra/i3/2104845057/TB2Lyp_g8USMeJjy1zkXXaWmpXa_!!2104845057.jpg")

    //文本
    private val text = arrayOf(
        "    是什么？嗯？",
        "    等一下你听我狡辩！",
        "    是什么蒙蔽了你？")


    fun initData(): ArrayList<Box>{
        val list : ArrayList<Box> = ArrayList()
        for (i in 1..NUMBER){

            list.add(Box(Img2t1[(1..Img2t1.size).random()-1],text[1], "2"))
        }
        return list
    }

    fun initDataB(): ArrayList<Box>{
        val list : ArrayList<Box> = ArrayList()
        for (i in 1..NUMBER){
            list.add(Box(Img1t1[(1..Img1t1.size).random()-1],text[0], text[2]))
        }
        return list
    }



}