package org.d3if4115.carlistsideproject.adapter

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_detail.view.*
import org.d3if4115.carlistsideproject.R
import org.d3if4115.carlistsideproject.fragment.ListCarsFragment
import org.d3if4115.carlistsideproject.util.CarList

class ListCarsAdapter : RecyclerView.Adapter<ListCarsAdapter.ListViewHolder>() {
    private var mBundle: Bundle? = Bundle()


    private val listCars = ArrayList<CarList>()

    fun setData(item: ArrayList<CarList>) {
        listCars.clear()
        listCars.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_cars, parent, false)
        )
    }

    override fun getItemCount(): Int = listCars.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listCars[position])
        holder.itemView.setOnClickListener(
            CustomOnItemClickListener(
                position,
                object : CustomOnItemClickListener.OnItemClickCallback {

                    override fun onItemClicked(view: View?, position: Int) {

//                        Toast.makeText(
//                            view?.context,
//                            listUserGithub[position].name,
//                            Toast.LENGTH_LONG
//                        ).show()
                        mBundle?.putString(ListCarsFragment.EXTRA_NAME,listCars[position].name)
                        mBundle?.putString(ListCarsFragment.EXTRA_DESCRIPTION,listCars[position].description)
                        mBundle?.putString(ListCarsFragment.EXTRA_PHOTO,listCars[position].photo)
                        view?.findNavController()?.navigate(R.id.action_listCarsFragment_to_detailFragment, mBundle)
                    }

                })
        )
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(carList: CarList) {

            val imageResource: Int =
                itemView.getResources()
                    .getIdentifier(carList.photo, null, itemView.context.packageName)

            val res: Drawable = itemView.getResources().getDrawable(imageResource)
            println(res)
            with(itemView) {
                Glide.with(itemView.context)
                    .load(imageResource)
                    .apply(RequestOptions().override(64, 64))
                    .into(img_profile)

                txt_name.text = carList.name
                txt_username.text = carList.description
            }
        }
    }
}