package duyndph34554.fpoly.app_dat_com_tam.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import duyndph34554.fpoly.app_dat_com_tam.room.dao.OrderDao
import duyndph34554.fpoly.app_dat_com_tam.room.database.AppDatabase
import duyndph34554.fpoly.app_dat_com_tam.room.model.OrderModel
import kotlinx.coroutines.launch

class OrderViewModel(application: Application) : AndroidViewModel(application) {
    private val orderDao: OrderDao = AppDatabase.getDatabase(application).orderDao()
    val allOrders: LiveData<List<OrderModel>> = orderDao.getAllOrders()

    fun insertOrder(order: OrderModel) {
        viewModelScope.launch {
            orderDao.insertOrder(order)
        }
    }
}
