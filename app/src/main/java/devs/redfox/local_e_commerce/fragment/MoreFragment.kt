package devs.redfox.local_e_commerce.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devs.redfox.local_e_commerce.R
import devs.redfox.local_e_commerce.adapter.AllOrderAdapter
import devs.redfox.local_e_commerce.databinding.FragmentMoreBinding
import devs.redfox.local_e_commerce.model.AllOrderModel


class MoreFragment : Fragment() {

    private lateinit var binding: FragmentMoreBinding
    private lateinit var list: ArrayList<AllOrderModel>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoreBinding.inflate(layoutInflater)
        list = ArrayList()

        val preferences =
            requireContext().getSharedPreferences("user", AppCompatActivity.MODE_PRIVATE)

        val adapter = AllOrderAdapter(list, requireContext())

        Firebase.firestore.collection("allOrders")
            .whereEqualTo("userId", preferences.getString("number", "")!!).get()
            .addOnSuccessListener {
                list.clear()
                for (doc in it) {
                    val data = doc.toObject(AllOrderModel::class.java)
                    list.add(data)
                    adapter.notifyDataSetChanged()
                }
                adapter.notifyDataSetChanged()
                binding.rvMoreFragment.adapter = AllOrderAdapter(list, requireContext())
                adapter.notifyDataSetChanged()
            }

        binding.swipeR.setOnRefreshListener {
            adapter.notifyDataSetChanged()
            Handler().postDelayed(Runnable {
                binding.swipeR.isRefreshing = false
            }, 4000)
        }

        return binding.root
    }


}