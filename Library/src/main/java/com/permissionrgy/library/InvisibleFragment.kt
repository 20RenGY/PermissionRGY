package com.permissionrgy.library

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment
import java.security.Permissions

//typealias关键字可以用于给任意类型指定一个别名，比
//如我们将(Boolean, List<String>) -> Unit的别名指定成了PermissionCallback
typealias PermissionCallBack = (Boolean, List<String>) -> Unit
class InvisibleFragment: Fragment() {

    private var callback:PermissionCallBack?=null
    fun requestNow(cb:PermissionCallBack,vararg permissions: String){
        callback=cb
        requestPermissions(permissions ,1)
    }


    override fun onRequestPermissionsResult(requestCode:Int, permissions: Array<String>,grantResults:IntArray){
        if (requestCode==1){
            val deniedList=ArrayList<String>()
            for ((index,result) in grantResults.withIndex()){
                if (result!=PackageManager.PERMISSION_GRANTED){
                    deniedList.add(permissions[index])
                }
            }
            val allGranted=deniedList.isEmpty()
            callback?.let { it(allGranted,deniedList) }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}