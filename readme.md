# 安卓APP模仿 OLD
使用 Fragment + ViewPager2 + BottomNavigation + Okhttp3等 模仿制作主流APP制作的模板

--- 
minSdkVersion 26  
compileSdkVersion 30  
buildToolsVersion "30.0.3"  
grandel_version = gradle-6.5-all  
kotlin_version = 1.4.31  

---
## 待做

---

### 优化日记

 02.26 加入了初始化界面，把一秒内并发接近500M内存平均到两秒200M多(还剩一秒不知道干嘛)

 02.27 使用了ViewPager2代替ViewPager，不要太香哦

 02.28 fragment中使用SparseArray替换ArrayList提升15%性能！

 03.03 fragment + ViewPager2实现了懒加载，打开时峰值300M-平均200M±。加载视图时候120M，滑动时不到125M

 03.05 再次优化多线程，现在打开时峰值只有280M±平均210M±，每多保持一个只有一个图片的fragment增加33M
