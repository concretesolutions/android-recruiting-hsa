package com.cadiz.accenture_test.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PullRequest (val id:String, val title: String, val body: String, val login: String, val avatar_url: String,
                   val html_url: String) : Parcelable