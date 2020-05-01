package nl.hva.level3example.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reminder(val reminderText: String) : Parcelable
