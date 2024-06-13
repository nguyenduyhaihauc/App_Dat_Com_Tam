package duyndph34554.fpoly.app_dat_com_tam.ui.utils
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import duyndph34554.fpoly.app_dat_com_tam.room.database.MyDatabase
import duyndph34554.fpoly.app_dat_com_tam.room.responsitory.TypeRiceResponsitory
import duyndph34554.fpoly.app_dat_com_tam.room.responsitory.TypeRiceViewModelFactory
import duyndph34554.fpoly.app_dat_com_tam.ui.viewmodel.TypeRiceViewModel

@Composable
fun provideTypeRiceViewModel(context: Context): TypeRiceViewModel {
    val database = remember { MyDatabase.getInstance(context) }
    val typeRiceRepository = remember { TypeRiceResponsitory(database) }
    val viewModelFactory = remember { TypeRiceViewModelFactory(typeRiceRepository) }
    return viewModel(factory = viewModelFactory)
}
