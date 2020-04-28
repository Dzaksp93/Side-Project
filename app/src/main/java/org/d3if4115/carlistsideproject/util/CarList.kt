package org.d3if4115.carlistsideproject.util

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CarList(
    val name: String,
    val description: String,
    val photo: String
) : Parcelable