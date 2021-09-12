package com.permissionrgy.library

import androidx.fragment.app.FragmentActivity

object PermissionRGY {

    private const val TAG="InvisibleFragment"
    fun request(activity:FragmentActivity,vararg permissions:String,callback:PermissionCallBack){
        var fragmentManager=activity.supportFragmentManager
        var existedFragment=fragmentManager.findFragmentByTag(TAG)
        var fragment=if (existedFragment!=null){
            existedFragment as InvisibleFragment
        }else{
            val invisibleFragment=InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback,*permissions)
    }
}