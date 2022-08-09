package uz.gita.contactappfragments.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(resLayout: Int): View {
    return LayoutInflater.from(context).inflate(resLayout, this, false)
}