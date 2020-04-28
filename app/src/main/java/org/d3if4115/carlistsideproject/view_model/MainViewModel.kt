package org.d3if4115.carlistsideproject.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.d3if4115.carlistsideproject.util.CarList
import org.json.JSONObject
import timber.log.Timber
import java.io.IOException
import java.io.InputStream

class MainViewModel : ViewModel() {
    private val listCar: MutableLiveData<ArrayList<CarList>> =
        MutableLiveData<ArrayList<CarList>>()

    fun readJSONFile(inputStream: InputStream) {
        val listItems: ArrayList<CarList> = ArrayList<CarList>()
        var json: String? = null
        try {

            json = inputStream.bufferedReader().use { it.readText() }
            Timber.i("This data JSON: $json")
            val jsonObject = JSONObject(json)
            val dataArray = jsonObject.getJSONArray("Cars")
            for (i in 0 until dataArray.length()) {
                val Cars: JSONObject = dataArray.getJSONObject(i)

                val name = Cars.getString("name")
                val description = Cars.getString("description")
                val avatar = Cars.getString("avatar")

                val carList = CarList(
                    name,
                    description,
                    avatar
                )

                listItems.add(carList)
            }
            listCar.postValue(listItems)
        } catch (e: IOException) {
            Timber.i(e)
        }
    }

    fun getCarList(): LiveData<ArrayList<CarList>> {
        return listCar
    }
}