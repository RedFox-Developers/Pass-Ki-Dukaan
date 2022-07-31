package devs.redfox.local_e_commerce.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import devs.redfox.local_e_commerce.R
import devs.redfox.local_e_commerce.adapter.CartAdapter
import devs.redfox.local_e_commerce.database.AppDatabase
import devs.redfox.local_e_commerce.database.ProductModel
import devs.redfox.local_e_commerce.databinding.FragmentCartBinding
import devs.redfox.local_e_commerce.databinding.FragmentHomeBinding


class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding
        get() = _binding!!
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

        dao.getAllProducts().observe(requireActivity()){
            binding.rvCartFragment.adapter = CartAdapter(requireContext(),
                it as ArrayList<ProductModel>
            )
        }

        return binding.root
    }

}