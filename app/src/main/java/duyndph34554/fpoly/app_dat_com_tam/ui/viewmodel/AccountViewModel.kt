package duyndph34554.fpoly.app_dat_com_tam.ui.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import duyndph34554.fpoly.app_dat_com_tam.room.database.AccountDb
import duyndph34554.fpoly.app_dat_com_tam.room.model.Account
import duyndph34554.fpoly.app_dat_com_tam.room.responsitory.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AccountViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AccountRepository

    init {
        val accountDb = AccountDb.getInstance(application)
        repository = AccountRepository(accountDb)
    }

    fun getAccountByUsernameAndPassword(userName: String, passWord: String): Flow<Account?> {
        return repository.authenticateUser(userName, passWord)
    }

    fun addAccount(account: Account) {
        viewModelScope.launch {
            repository.addAccountToRoom(account)
        }
    }

    fun deleteAccount(account: Account) {
        viewModelScope.launch {
            repository.deleteAccount(account)
        }
    }

    fun updateAccount(account: Account) {
        viewModelScope.launch {
            repository.updateAccount(account)
        }
    }

    fun getAllAccounts(): Flow<List<Account>> {
        return repository.getAllAccountDb()
    }
}
