package duyndph34554.fpoly.app_dat_com_tam.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import duyndph34554.fpoly.app_dat_com_tam.model.FoodModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao{
    @Query("SELECT * FROM foodmodel")
    fun getAllFood(): Flow<List<FoodModel>>

    @Query("SELECT * FROM foodmodel WHERE foodid IN (:foodIds)")
    fun loadAllByIds(foodIds: IntArray): Flow<FoodModel>
    @Query("SELECT * FROM foodmodel WHERE foodid = :foodId")
    fun getFoodById(foodId: Int): Flow<FoodModel>
    @Insert
    suspend fun insertFood(vararg foodModel: FoodModel)
    @Delete
    suspend fun deleteFood(foodModel: FoodModel)

    @Update
    suspend fun updateFood(foodModel: FoodModel)
}