package uz.gita.contactappfragments.appdatabase.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(t: T)

    @Insert
    fun insertAll(t: List<T>)

    @Delete
    fun delete(t: T)

    @Delete
    fun deleteAll(t: List<T>)

}