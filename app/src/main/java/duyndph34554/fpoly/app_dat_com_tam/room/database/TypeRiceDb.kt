package duyndph34554.fpoly.app_dat_com_tam.room.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import duyndph34554.fpoly.app_dat_com_tam.room.dao.TypeRiceDao
import duyndph34554.fpoly.app_dat_com_tam.room.model.TypeRice

@Database(entities = [TypeRice::class], version = 1, exportSchema = false)
abstract class TypeRiceDb : RoomDatabase() {

    abstract fun typeRiceDao(): TypeRiceDao

    companion object {

        @Volatile
        private var INTANCE: TypeRiceDb? = null
        fun getIntance(context: Context): TypeRiceDb {
            synchronized(this){
                var intance = INTANCE
                if (intance == null){
                    intance = Room.databaseBuilder(
                        context.applicationContext,
                        TypeRiceDb::class.java,
                        "type-rice"
                    ).build()
                }
                return intance
            }

        }

    }

}

