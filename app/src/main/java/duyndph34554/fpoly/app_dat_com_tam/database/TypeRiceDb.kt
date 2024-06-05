package duyndph34554.fpoly.app_dat_com_tam.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.RoomDatabase
import duyndph34554.fpoly.app_dat_com_tam.dao.TypeRiceDao
import duyndph34554.fpoly.app_dat_com_tam.model.TypeRice

@Database(entities = arrayOf(TypeRice::class), version = 1)
abstract class TypeRiceDb : RoomDatabase() {
    abstract fun typeRiceDao(): TypeRiceDao
}
