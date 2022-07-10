package devs.redfox.local_e_commerce.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devs.redfox.local_e_commerce.R
import devs.redfox.local_e_commerce.databinding.ActivityCategoryBinding
import devs.redfox.local_e_commerce.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)

        getProductDetails(intent.getStringExtra("id"))
        setContentView(binding.root)

    }

    private fun getProductDetails(proId: String?) {

        Firebase.firestore.collection("products")
            .document(proId!!).get().addOnSuccessListener {
                val list = it.get("productImages") as ArrayList<String>
                binding.textView7.text = it.getString("productName")
                binding.textView8.text = it.getString("productSp")
                binding.textView9.text = it.getString("productDescription")

                val slideList = ArrayList<SlideModel>()
                for(data in list) {
                slideList.add(SlideModel(data,ScaleTypes.CENTER_CROP))
                }

                binding.imageSlider.setImageList(slideList)

            }.addOnFailureListener {
        Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }
}