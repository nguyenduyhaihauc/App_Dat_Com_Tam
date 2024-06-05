package duyndph34554.fpoly.app_dat_com_tam.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import duyndph34554.fpoly.app_dat_com_tam.model.TypeRice
import kotlinx.coroutines.flow.Flow

interface TypeRiceDao {
    @Query("SELECT * FROM typerice")
    fun getAll(): Flow<List<TypeRice>>

    @Query("SELECT * FROM typerice WHERE typeRiceId IN (:typeRiceIds)")
    fun loadAllByIds(typeRiceIds: IntArray): Flow<TypeRice>

    @Insert
    suspend fun insertAll(vararg users: TypeRice)

    @Update
    suspend fun update(typerice: TypeRice)

    @Delete
    suspend fun delete(typerice: TypeRice)


}