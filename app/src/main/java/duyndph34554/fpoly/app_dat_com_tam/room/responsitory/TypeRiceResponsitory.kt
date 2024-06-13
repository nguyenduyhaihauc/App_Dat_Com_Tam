package duyndph34554.fpoly.app_dat_com_tam.room.responsitory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import duyndph34554.fpoly.app_dat_com_tam.room.database.MyDatabase
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice
import duyndph34554.fpoly.app_dat_com_tam.ui.viewmodel.TypeRiceViewModel

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
class TypeRiceViewModelFactory(
    private val typeRiceRepository: TypeRiceResponsitory
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TypeRiceViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TypeRiceViewModel(typeRiceRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
