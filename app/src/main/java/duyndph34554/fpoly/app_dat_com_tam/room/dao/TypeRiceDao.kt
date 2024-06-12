package duyndph34554.fpoly.app_dat_com_tam.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice
import kotlinx.coroutines.flow.Flow

@Dao
interface TypeRiceDao {
    @Query("SELECT * FROM typerice")
    fun getAllTypeRice(): Flow<List<TypeRice>>

    @Query("SELECT * FROM typerice WHERE id IN (:typeRiceIds)")
    fun loadAllTypeRiceByIds(typeRiceIds: IntArray): Flow<TypeRice>

    @Insert
    suspend fun insertTypeRice(vararg typerice: TypeRice)

    @Update
    suspend fun updateTypeRice(typerice: TypeRice)

    @Delete
    suspend fun deleteTypeRice(typerice: TypeRice)


}