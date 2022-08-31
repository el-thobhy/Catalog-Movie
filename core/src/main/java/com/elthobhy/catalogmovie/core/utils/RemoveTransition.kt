package com.elthobhy.catalogmovie.core.utils

import android.app.Activity
import android.util.ArrayMap
import android.view.ViewGroup
import androidx.transition.Transition
import androidx.transition.TransitionManager
import java.lang.ref.WeakReference

@Suppress("UNCHECKED_CAST")
fun removeActivityFromTransitionManager(activity: Activity) {
    val transition = TransitionManager::class.java
    try {
        val field = transition.getDeclaredField("sRunningTransitions")
        field.isAccessible = true
        val runningTransitions: ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>> =
            field.get(transition) as ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>>
        if (runningTransitions.get() == null || runningTransitions.get()?.get() == null) {
            return
        }
        val map = runningTransitions.get()?.get()
        val decorView = activity.window.decorView
        if (map != null) {
            if (map.containsKey(decorView)) {
                map.remove(decorView)
            }
        }

    } catch (e: NoSuchFieldException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }
}