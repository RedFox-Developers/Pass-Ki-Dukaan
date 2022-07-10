package devs.redfox.local_e_commerce.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductModel (
    @PrimaryKey
    @NonNull
    val productId : String,
    @ColumnInfo(name = "productName")
    val productName : String? = "",
    @ColumnInfo(name = "")
    val productImage : String? = "productImage",
    @ColumnInfo(name = "productSp")
    val productSp : String? = "",

    )