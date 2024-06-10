package duyndph34554.fpoly.app_dat_com_tam.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice
import duyndph34554.fpoly.app_dat_com_tam.room.responsitory.TypeRiceResponsitory
import kotlinx.coroutines.launch

class TypeRiceViewModel(val typeRiceRepository: TypeRiceResponsitory) : ViewModel() {

    fun addTypeRice(typeRice: TypeRice) {
        viewModelScope.launch {
            typeRiceRepository.addTypeRiceToRoom(typeRice)
        }
    }

    val typeRices = typeRiceRepository.getAllTypeRiceDb()

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