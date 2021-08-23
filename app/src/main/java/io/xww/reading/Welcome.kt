package io.xww.reading

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.xww.reading.ui.MainActivity
import java.io.File


class Welcome : AppCompatActivity() {
    private val PERMISIONCODE = 15951
    private var startCode = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        Handler(Looper.getMainLooper()).postDelayed({ launcher() }, 3000)
        checkPermission()
        checkCaChe()
    }

    /**
     * 初始化
     */
    private fun launcher(){
        if (startCode >= 0) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    /**
     * 以下是各种检查，还没有用异步处理
     */
    private fun checkCaChe(){
        val cacheDir: File = Environment.getDownloadCacheDirectory()
        if (!cacheDir.exists()){
            cacheDir.mkdirs()
        }
    }

    /**
     * 禁止了SDK26版本以下手机的安装行为 OqO
     */
    private fun checkPermission(){
        val permission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        if (permission == PackageManager.PERMISSION_DENIED){
            startCode -= 1
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), PERMISIONCODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == PERMISIONCODE){
            startCode += 1
            launcher()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}