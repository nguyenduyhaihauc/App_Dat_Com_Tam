package duyndph34554.fpoly.app_dat_com_tam.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import duyndph34554.fpoly.app_dat_com_tam.room.dao.FoodDao
import duyndph34554.fpoly.app_dat_com_tam.model.FoodModel

@Database(entities = arrayOf(FoodModel::class), version = 1)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}