package duyndph34554.fpoly.app_dat_com_tam.room.responsitory

import duyndph34554.fpoly.app_dat_com_tam.room.database.MyDatabase
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice

class TypeRiceResponsitory(val typeRiceDb: MyDatabase) {
    suspend fun addTypeRiceToRoom(typeRice: TypeRice) {
        typeRiceDb.typeRiceDao().insertTypeRice(typeRice)
    }

    fun getAllTypeRiceDb() = typeRiceDb.typeRiceDao().getAllTypeRice()

    suspend fun deleteTypeRice(typeRice: TypeRice) {
        typeRiceDb.typeRiceDao().deleteTypeRice(typeRice)
    }

    suspend fun updateTypeRice(typeRice: TypeRice) {
        typeRiceDb.typeRiceDao().updateTypeRice(typeRice)
    }

}