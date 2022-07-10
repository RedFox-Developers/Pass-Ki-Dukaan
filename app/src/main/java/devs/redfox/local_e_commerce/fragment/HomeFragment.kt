package devs.redfox.local_e_commerce.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import devs.redfox.local_e_commerce.R
import devs.redfox.local_e_commerce.activity.ProductDetailActivity
import devs.redfox.local_e_commerce.adapter.CategoryAdapter
import devs.redfox.local_e_commerce.adapter.ProductAdapter
import devs.redfox.local_e_commerce.databinding.FragmentHomeBinding
import devs.redfox.local_e_commerce.model.AddProductModel
import devs.redfox.local_e_commerce.model.CategoryModel


class HomeFragment : Fragment() {
    var db = FirebaseFirestore.getInstance()
    private var _binding: FragmentHomeBinding? = null
    private val binding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.button2.setOnClickListener {
            val intent = Intent(requireContext(),ProductDetailActivity::class.java)
            startActivity(intent)
        }

        val preference = requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)

        if(preference.getBoolean("isCart",true))
            findNavController().navigate(R.id.action_homeFragment_to_cartFragment)

        getCategories()
        getSliderImage()
        getProducts()

        return binding.root
    }

    private fun getProducts(){
        val list = ArrayList<AddProductModel>()
        Firebase.firestore.collection("products")
            .get().addOnSuccessListener {
                list.clear()
                for(doc in it.documents){
                    val data = doc.toObject(AddProductModel::class.java)
                    list.add(data!!)
                }
                binding.productRecycler.adapter = ProductAdapter(requireContext(),list)
            }
    }

    private fun getCategories(){
        val list = ArrayList<CategoryModel>()
        Firebase.firestore.collection("categories")
            .get().addOnSuccessListener {
                list.clear()
                for(doc in it.documents){
                    val data = doc.toObject(CategoryModel::class.java)
                    list.add(data!!)
                }
                binding.categoryRecycler.adapter = CategoryAdapter(requireContext(),list)
            }
    }

    private fun getSliderImage(){
        Firebase.firestore.collection("slider").document("item")
            .get().addOnSuccessListener {
                Glide.with(requireContext()).load(it.get("img")).into(binding.sliderImage)
            }
    }

}