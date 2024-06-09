package duyndph34554.fpoly.app_dat_com_tam.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Entity
data class FoodModel(
    @PrimaryKey(autoGenerate = true) var foodid: Int = 0,
    @ColumnInfo(name = "ten san pham") var namefood: String,
    @ColumnInfo(name = "kieu san pham") var typefood: String,
    @ColumnInfo(name = "gia san pham") var pricefood: Double,
    @ColumnInfo(name = "anh san pham") var imageurl: String
)

@Dao
interface FoodDao{
    @Query("SELECT * FROM foodmodel")
    fun getAllFood(): Flow<List<FoodModel>>

    @Query("SELECT * FROM foodmodel WHERE foodid IN (:foodIds)")
    fun loadAllByIds(foodIds: IntArray):Flow<FoodModel>
    @Query("SELECT * FROM foodmodel WHERE foodid = :foodId")
    fun getFoodById(foodId: Int): Flow<FoodModel>
    @Insert
    suspend fun insertFood(vararg foodModel: FoodModel)
    @Delete
    suspend fun deleteFood(foodModel: FoodModel)

    @Update
    suspend fun updateFood(foodModel: FoodModel)
}

@Database(entities = arrayOf(FoodModel::class), version = 1)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}
