package devs.redfox.local_e_commerce.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import devs.redfox.local_e_commerce.databinding.CategoryItemBinding
import devs.redfox.local_e_commerce.model.CategoryModel


class CategoryAdapter(var context: Context, val list:ArrayList<CategoryModel>):
RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

     class CategoryViewHolder(val binding: CategoryItemBinding, val context: Context):
         RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):CategoryViewHolder{
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CategoryItemBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.binding.textView.text= list[position].cat
        Glide.with(context).load(list[position].img).into(holder.binding.imageView)
    }

    override fun getItemCount(): Int{
        return list.size
    }

}