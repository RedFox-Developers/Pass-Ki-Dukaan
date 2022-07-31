package devs.redfox.local_e_commerce.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devs.redfox.local_e_commerce.activity.ProductDetailActivity
import devs.redfox.local_e_commerce.database.AppDatabase
import devs.redfox.local_e_commerce.database.ProductModel
import devs.redfox.local_e_commerce.databinding.LayoutCartItemBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

        holder.itemView.setOnClickListener {
            val intent = Intent(context, ProductDetailActivity::class.java)
            intent.putExtra("id", list[position].productId)
            context.startActivity(intent)
        }


        val dao = AppDatabase.getInstance(context).productDao()
        holder.binding.btnDeleteCartItem.setOnClickListener {

            GlobalScope.launch(Dispatchers.IO) {
                dao.deleteProduct(
                    ProductModel(
                        list[position].productId,
                        list[position].productName,
                        list[position].productImage,
                        list[position].productSp
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}