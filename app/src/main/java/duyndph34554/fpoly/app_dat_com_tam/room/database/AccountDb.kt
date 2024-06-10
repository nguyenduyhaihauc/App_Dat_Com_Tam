package duyndph34554.fpoly.app_dat_com_tam.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import duyndph34554.fpoly.app_dat_com_tam.room.dao.AccountDao
import duyndph34554.fpoly.app_dat_com_tam.room.model.Account
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Account::class], version = 1, exportSchema = false)
abstract class AccountDb : RoomDatabase() {

    abstract fun accountDao(): AccountDao

    companion object {
        @Volatile
        private var INSTANCE: AccountDb? = null

        fun getInstance(context: Context): AccountDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AccountDb::class.java,
                    "account2"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(AccountDatabaseCallback())
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class AccountDatabaseCallback : RoomDatabase.Callback() {
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
