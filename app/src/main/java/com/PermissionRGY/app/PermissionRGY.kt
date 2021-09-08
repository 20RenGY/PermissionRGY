package com.PermissionRGY.app

import androidx.fragment.app.FragmentActivity

object PermissionRGY {
    private const val TAG = "InvisibleFragment"
    fun request(activity:FragmentActivity,vararg permissions:String,callback:PermissionCallback){
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existedFragment != null) {
            existedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        //*，这个符号并不是指针的意思，而是表示将一个数组转换成可变长度参数传递过去。
        fragment.requestNow(callback, *permissions)
    }
}