package com.cadiz.accenture_test.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Repository(val id:String, val name: String, val description: String, val autor: String, val avatar_url: String,
                      val star_count: Int, val fork_count: Int, val login: String) : Parcelable


