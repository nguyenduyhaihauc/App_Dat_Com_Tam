package duyndph34554.fpoly.app_dat_com_tam.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice
import duyndph34554.fpoly.app_dat_com_tam.room.responsitory.TypeRiceResponsitory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class TypeRiceViewModel(private val typeRiceRepository: TypeRiceResponsitory) : ViewModel() {

    val typeRices: Flow<List<TypeRice>> = typeRiceRepository.getAllTypeRiceDb()

    fun addTypeRice(typeRice: TypeRice) {
        viewModelScope.launch {
            typeRiceRepository.addTypeRiceToRoom(typeRice)
        }
    }

    fun deleteTypeRice(typeRice: TypeRice) {
        viewModelScope.launch {
            typeRiceRepository.deleteTypeRice(typeRice)
        }
    }

    fun updateTypeRice(typeRice: TypeRice) {
        viewModelScope.launch {
            typeRiceRepository.updateTypeRice(typeRice)
        }
    }
}

