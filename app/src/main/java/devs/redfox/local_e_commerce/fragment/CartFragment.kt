package devs.redfox.local_e_commerce.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import devs.redfox.local_e_commerce.R
import devs.redfox.local_e_commerce.activity.AddressActivity
import devs.redfox.local_e_commerce.activity.CategoryActivity
import devs.redfox.local_e_commerce.adapter.CartAdapter
import devs.redfox.local_e_commerce.database.AppDatabase
import devs.redfox.local_e_commerce.database.ProductModel
import devs.redfox.local_e_commerce.databinding.FragmentCartBinding
import devs.redfox.local_e_commerce.databinding.FragmentHomeBinding


class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var list : ArrayList<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        val preference = requireContext().getSharedPreferences("info", AppCompatActivity.MODE_PRIVATE)
        val editor = preference.edit()
        editor.putBoolean("isCart",false)
        editor.apply()

        val dao = AppDatabase.getInstance(requireContext()).productDao()

        list = ArrayList()

        dao.getAllProducts().observe(requireActivity()){
            binding.rvCartFragment.adapter = CartAdapter(requireContext(),
                it as ArrayList<ProductModel>
            )

            list.clear()
            for(data in it){
                list.add(data.productId )
            }
            totalCost(it)
        }

        return binding.root
    }

    private fun totalCost(data: java.util.ArrayList<ProductModel>) {
        var total = 0
        for(item in data!!){
            total += item.productSp!!.toInt()
        }

        binding.textView13.text = "Total item in cart: ${data.size}"
        binding.textView14.text = "Total Cost: $total"

        binding.checkout.setOnClickListener {
            val intent = Intent(context, AddressActivity::class.java)
            intent.putExtra("totalCost", total)
            intent.putExtra("productIds", list)
            startActivity(intent)
        }
    }

}