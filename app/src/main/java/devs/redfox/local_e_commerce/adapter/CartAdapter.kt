package devs.redfox.local_e_commerce.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devs.redfox.local_e_commerce.database.ProductModel
import devs.redfox.local_e_commerce.databinding.LayoutCartItemBinding

class CartAdapter(val context: Context, val list: ArrayList<ProductModel>): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding:LayoutCartItemBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = LayoutCartItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {

        Glide.with(context).load(list[position].productImage).into(holder.binding.imageView4)
        holder.binding.textView11.text = list[position].productName
        holder.binding.textView12.text = list[position].productSp
    }

    override fun getItemCount(): Int {
        return list.size
    }
}