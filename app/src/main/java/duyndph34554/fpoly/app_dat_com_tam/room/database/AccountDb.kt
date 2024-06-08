package duyndph34554.fpoly.app_dat_com_tam.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import duyndph34554.fpoly.app_dat_com_tam.room.dao.AccountDao
import duyndph34554.fpoly.app_dat_com_tam.room.database.AccountDb.Companion.getInstance
import duyndph34554.fpoly.app_dat_com_tam.room.model.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

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
                    "account"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        fun populateInitialData(context: Context) {
            runBlocking {
                withContext(Dispatchers.IO) {
                    val db = getInstance(context)
                    val accountDao = db.accountDao()

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
                            userName = "toán",
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
    }
}
