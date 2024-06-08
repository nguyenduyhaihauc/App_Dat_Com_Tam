package duyndph34554.fpoly.app_dat_com_tam.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    @PrimaryKey(autoGenerate = true) var accountId: Int = 0,
    @ColumnInfo(name = "imageUrl") var imageUrl: String?,
    @ColumnInfo(name = "userName") var userName: String,
    @ColumnInfo(name = "passWord") var passWord: String,
    @ColumnInfo(name = "fullName") var fullName: String?,
    @ColumnInfo(name = "address") var address: String?,
    @ColumnInfo(name = "role") var role: String
)
