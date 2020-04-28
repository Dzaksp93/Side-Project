package org.d3if4115.carlistsideproject.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_cars.*

import org.d3if4115.carlistsideproject.R
import org.d3if4115.carlistsideproject.adapter.ListCarsAdapter
import org.d3if4115.carlistsideproject.util.CarList
import org.d3if4115.carlistsideproject.view_model.MainViewModel
import java.io.InputStream

/**
 * A simple [Fragment] subclass.
 */
class ListCarsFragment : Fragment() {

    companion object {
        val EXTRA_NAME = "extra_name"
        val EXTRA_DESCRIPTION = "extra_description"
        val EXTRA_PHOTO = "extra_avatar"
    }

    lateinit var adapter: ListCarsAdapter
    lateinit var mainViewModel : MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_cars, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListCarsAdapter()
        adapter.notifyDataSetChanged()

        rv_listCars.setHasFixedSize(true)
        rv_listCars.layoutManager = LinearLayoutManager(activity)
        rv_listCars.adapter = adapter

        mainViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        mainViewModel.getCarList().observe(requireActivity(), Observer { CarList ->
            if (CarList != null){
                adapter.setData(CarList)
            }
        })

        val inputStream: InputStream = requireActivity().assets.open("CarList.json")
        mainViewModel.readJSONFile(inputStream)

    }

}
