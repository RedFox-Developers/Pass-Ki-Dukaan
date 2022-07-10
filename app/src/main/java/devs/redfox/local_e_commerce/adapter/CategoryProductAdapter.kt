package devs.redfox.local_e_commerce.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devs.redfox.local_e_commerce.activity.ProductDetailActivity
import devs.redfox.local_e_commerce.databinding.ItemCategoryProductBinding
import devs.redfox.local_e_commerce.model.AddProductModel

class CategoryProductAdapter(val context: Context, val list : ArrayList<AddProductModel>)
    : RecyclerView.Adapter<CategoryProductAdapter.CategoryProductViewHolder>() {

    class CategoryProductViewHolder(val binding: ItemCategoryProductBinding, val context: Context):
        RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCategoryProductBinding.inflate(layoutInflater, parent, false)
        return CategoryProductViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: CategoryProductViewHolder, position: Int) {
        Glide.with(context).load(list[position].productCoverImg).into(holder.binding.imageView3)

        holder.binding.textView5.text = list[position].productName
        holder.binding.textView6.text = list[position].productSp

        holder.itemView.setOnClickListener {
            val intent = Intent(context,ProductDetailActivity::class.java)
            intent.putExtra("id",list[position].productId)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}