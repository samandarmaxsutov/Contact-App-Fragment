package uz.gita.contactappfragments.appdatabase.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhoneNumberEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val number: String,
    @ColumnInfo(name = "contact_id")
    val contactId: Int
)
