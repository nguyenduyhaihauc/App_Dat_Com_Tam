package duyndph34554.fpoly.app_dat_com_tam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice

@Entity(foreignKeys = [ForeignKey(entity = TypeRice::class,
    parentColumns = ["id"],
    childColumns = ["typeRiceId"],
    onDelete = ForeignKey.CASCADE)])

data class FoodModel(
    @PrimaryKey(autoGenerate = true) var foodid: Int = 0,
    @ColumnInfo(name = "ten san pham") var namefood: String,
    @ColumnInfo(name = "kieu san pham") var typefood: String,
    @ColumnInfo(name = "gia san pham") var pricefood: Double,
    @ColumnInfo(name = "anh san pham") var imageurl: String,
    @ColumnInfo(name = "typeRiceId") var typeRiceId: Int
)
