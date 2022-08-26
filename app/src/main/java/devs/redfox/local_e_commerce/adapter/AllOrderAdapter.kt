package devs.redfox.local_e_commerce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devs.redfox.local_e_commerce.databinding.AllOrderItemLayoutBinding
import devs.redfox.local_e_commerce.model.AllOrderModel

//class start
class AllOrderAdapter(val list: ArrayList<AllOrderModel>, val context: Context) :
    RecyclerView.Adapter<AllOrderAdapter.AllOrderViewHolder>() {

    inner class AllOrderViewHolder(val binding: AllOrderItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllOrderViewHolder {
        return AllOrderViewHolder(
            AllOrderItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: AllOrderViewHolder, position: Int) {
        holder.binding.productTitle.text = list[position].name
        holder.binding.productPrice.text = list[position].price



        when (list[position].status) {
            "Ordered" -> {
                holder.binding.productStatus.text = "Order Status: Ordered"

            }
            "Dispatched" -> {
                holder.binding.productStatus.text = "Order Status: Dispatched"

            }
            "Delivered" -> {
                holder.binding.productStatus.text = "Order Status: Delivered"

            }
            "Canceled" -> {
                holder.binding.productStatus.text = "Order Status: Cancelled"

            }

        }

    }


    override fun getItemCount(): Int {
        return list.size
    }
}
