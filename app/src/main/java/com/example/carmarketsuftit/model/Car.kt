package com.example.carmarketsuftit.model

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.carmarketsuftit.utils.Converters
import java.io.Serializable

@Entity(tableName = "car_table")
@TypeConverters(Converters::class)
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val creationYear: Long,
    val engine_capacity: Double,
    val image: Bitmap,
    val price: Double,
    val publicationDate: String
) : Serializable
