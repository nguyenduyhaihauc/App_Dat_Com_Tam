package duyndph34554.fpoly.app_dat_com_tam.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import duyndph34554.fpoly.app_dat_com_tam.room.dao.OrderDao
import duyndph34554.fpoly.app_dat_com_tam.room.database.MyDatabase
import duyndph34554.fpoly.app_dat_com_tam.room.model.OrderModel
import kotlinx.coroutines.launch

class OrderViewModel(application: Application) : AndroidViewModel(application) {
    private val orderDao: OrderDao = MyDatabase.getInstance(application).orderDao()
    val allOrders: LiveData<List<OrderModel>> = orderDao.getAllOrders()

    fun insertOrder(order: OrderModel) {
        viewModelScope.launch {
            orderDao.insertOrder(order)
        }
    }

    fun updateOrderStatus(nameOrder: String, status: Boolean) {
        viewModelScope.launch {
            val order = orderDao.getOrderByName(nameOrder)
            if (order != null) {
                order.status = status
                orderDao.updateOrder(order)
            }
        }
    }
}




