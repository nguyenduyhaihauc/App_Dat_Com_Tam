package duyndph34554.fpoly.app_dat_com_tam.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var nameOrder: String,
    var totalAmount: Double,
    var status: Boolean
)

