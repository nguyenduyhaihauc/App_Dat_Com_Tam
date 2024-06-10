package duyndph34554.fpoly.app_dat_com_tam.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import duyndph34554.fpoly.app_dat_com_tam.room.dao.OrderDao
import duyndph34554.fpoly.app_dat_com_tam.room.model.OrderModel


@Database(entities = [OrderModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "order_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}