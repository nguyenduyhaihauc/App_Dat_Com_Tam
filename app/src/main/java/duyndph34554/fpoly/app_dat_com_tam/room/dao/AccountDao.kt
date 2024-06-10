package duyndph34554.fpoly.app_dat_com_tam.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import duyndph34554.fpoly.app_dat_com_tam.room.model.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Query("SELECT * FROM account")
    fun getAllAccount(): Flow<List<Account>>

    @Query("SELECT * FROM account WHERE accountId IN (:accountIds)")
    fun loadAllAccountByIds(accountIds: IntArray): Flow<Account>

    @Query("SELECT * FROM Account WHERE userName = :userName AND passWord = :passWord")
    fun getAccountByUsernameAndPassword(userName: String, passWord: String): Flow<Account?>

    @Insert
    suspend fun insertAccount(vararg account: Account)

    @Update
    suspend fun updateAccount(account: Account)

    @Delete
    suspend fun deleteAccount(account: Account)


}