package devs.redfox.local_e_commerce.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devs.redfox.local_e_commerce.R
import devs.redfox.local_e_commerce.adapter.CategoryProductAdapter
import devs.redfox.local_e_commerce.adapter.ProductAdapter
import devs.redfox.local_e_commerce.databinding.ActivityCategoryBinding
import devs.redfox.local_e_commerce.databinding.ActivityMainBinding
import devs.redfox.local_e_commerce.model.AddProductModel

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getProducts(intent.getStringExtra("cat"))
    }

    private fun getProducts(category: String?) {
        val list = ArrayList<AddProductModel>()
        Firebase.firestore.collection("products").whereEqualTo("productCategory",category)
            .get().addOnSuccessListener {
                list.clear()
                for(doc in it.documents){
                    val data = doc.toObject(AddProductModel::class.java)
                    list.add(data!!)
                }
                binding.recyclerView.adapter = CategoryProductAdapter(this,list)
            }
    }

}