package com.example.brainbyte.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "spam_table")
@Parcelize
class SpamNumber (
    @PrimaryKey
    var number: String,
    var reports: Int
    ) : Parcelable {}