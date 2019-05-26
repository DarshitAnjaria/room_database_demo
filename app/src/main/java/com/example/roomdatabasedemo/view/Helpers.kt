package com.example.roomdatabasedemo.view

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun Context.snackbar(v:View, message:String, length:Int) =
        Snackbar.make(v,message,length).show()