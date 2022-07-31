package devs.redfox.local_e_commerce.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import devs.redfox.local_e_commerce.database.ProductModel
import devs.redfox.local_e_commerce.databinding.LayoutCartItemBinding

class CartAdapter(context: Context, list: ArrayList<ProductModel>): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(binding:LayoutCartItemBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {

    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {

    }
}