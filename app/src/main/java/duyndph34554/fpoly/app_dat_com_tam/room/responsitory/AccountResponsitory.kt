package duyndph34554.fpoly.app_dat_com_tam.room.responsitory

import duyndph34554.fpoly.app_dat_com_tam.room.database.MyDatabase
import duyndph34554.fpoly.app_dat_com_tam.room.model.Account
import kotlinx.coroutines.flow.Flow

class AccountRepository(val accountDb: MyDatabase) {
    suspend fun addAccountToRoom(account: Account) {
        accountDb.accountDao().insertAccount(account)
    }

    fun getAllAccountDb() = accountDb.accountDao().getAllAccount()

    suspend fun deleteAccount(account: Account) {
        accountDb.accountDao().deleteAccount(account)
    }

    suspend fun updateAccount(account: Account) {
        accountDb.accountDao().updateAccount(account)
    }

    fun authenticateUser(userName: String, passWord: String): Flow<Account?> {
        return accountDb.accountDao().getAccountByUsernameAndPassword(userName, passWord)
    }
}
