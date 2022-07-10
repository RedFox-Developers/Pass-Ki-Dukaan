package devs.redfox.local_e_commerce.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(vararg product:ProductModel)

    @Delete
    suspend fun deleteProduct(vararg product:ProductModel)

    @Query("SELECT * FROM products")
    fun getAllProducts(): LiveData<List<ProductModel>>

    @Query("SELECT * FROM products WHERE productId = :id")
    fun isExit(id : String) : ProductModel
}