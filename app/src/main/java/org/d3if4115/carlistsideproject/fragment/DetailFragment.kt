package org.d3if4115.carlistsideproject.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*

import org.d3if4115.carlistsideproject.R

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString(ListCarsFragment.EXTRA_NAME)
        val description = arguments?.getString(ListCarsFragment.EXTRA_DESCRIPTION)
        val photo = arguments?.getString(ListCarsFragment.EXTRA_PHOTO)

        txt_name.text = String.format("Car Type : %s",name)
        txt_username.text = String.format("Description : \n %s" , description)

        val imageResource: Int = getResources()
            .getIdentifier(photo, null, context?.packageName)

        val res: Drawable = getResources().getDrawable(imageResource)
        Glide.with(requireActivity())
            .load(res)
            .into(img_profile)
    }
}
