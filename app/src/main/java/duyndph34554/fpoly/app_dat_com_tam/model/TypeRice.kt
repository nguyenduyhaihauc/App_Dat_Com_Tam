package duyndph34554.fpoly.app_dat_com_tam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TypeRice(
    @PrimaryKey(autoGenerate = true) var typeRiceId: Int = 0,
    @ColumnInfo(name = "typeRiceName") var typeRiceName: String?,

)
