package duyndph34554.fpoly.app_dat_com_tam.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import duyndph34554.fpoly.app_dat_com_tam.model.FoodModel
import duyndph34554.fpoly.app_dat_com_tam.room.dao.AccountDao
import duyndph34554.fpoly.app_dat_com_tam.room.dao.FoodDao
import duyndph34554.fpoly.app_dat_com_tam.room.dao.OrderDao
import duyndph34554.fpoly.app_dat_com_tam.room.dao.TypeRiceDao
import duyndph34554.fpoly.app_dat_com_tam.room.model.Account
import duyndph34554.fpoly.app_dat_com_tam.room.model.OrderModel
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [TypeRice::class, FoodModel::class, OrderModel::class, Account::class], version = 3, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun typeRiceDao(): TypeRiceDao
    abstract fun foodDao(): FoodDao
    abstract fun orderDao(): OrderDao
    abstract fun accountDao(): AccountDao

    companion object {

        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getInstance(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "comtamm5.db"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database.accountDao())
                    }
                }
            }
        }

        suspend fun populateDatabase(accountDao: AccountDao) {
            val account1 = Account(
                userName = "quyet",
                passWord = "quyet",
                role = "admin",
                address = "Thanh Hóa",
                fullName = "TVQ",
                imageUrl = null
            )
            val account2 = Account(
                userName = "duy",
                passWord = "duy",
                role = "admin",
                address = "Nam Định",
                fullName = "NDD",
                imageUrl = null
            )
            val account3 = Account(
                userName = "toan",
                passWord = "toan",
                role = "admin",
                address = "Phú Thọ",
                fullName = "NTT",
                imageUrl = null
            )

            accountDao.insertAccount(account1)
            accountDao.insertAccount(account2)
            accountDao.insertAccount(account3)
        }
    }
}
