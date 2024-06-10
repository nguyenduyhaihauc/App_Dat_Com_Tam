package duyndph34554.fpoly.app_dat_com_tam.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import duyndph34554.fpoly.app_dat_com_tam.room.model.OrderModel

@Dao
interface OrderDao {
    @Query("SELECT * FROM orders")
    fun getAllOrders(): LiveData<List<OrderModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderModel)
}