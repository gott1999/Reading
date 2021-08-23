package io.xww.reading.data

import java.io.Serializable

// Box类，自动实现getter setter constructor，妙~啊~
data class Box(var img: String,var text: String,var title: String): Serializable