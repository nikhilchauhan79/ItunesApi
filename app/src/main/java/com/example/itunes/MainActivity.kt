package com.example.itunes

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.*
import android.util.Log
import android.view.Menu
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    private val REQUEST_WRITE_PERMISSION = 123
    private lateinit var adapter: RepoListAdapter
    private var itemsList = ArrayList<Results>()
    private var displayList = ArrayList<Results>()
    private var numToast=1
    private var displayListRoom = ArrayList<Results>()

    private var roomList = ArrayList<Results>()
    private lateinit var searchView: SearchView
    lateinit var recyclerView: RecyclerView

    lateinit var musicViewModel: MusicViewModel

    lateinit var context: Context


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view) as RecyclerView

        context = this@MainActivity

        musicViewModel = ViewModelProvider(this).get(MusicViewModel::class.java)





    }

    fun getOfflineData(data:String){
        Log.d("text rec", "filter: " + data)
        val temp: ArrayList<Results> = ArrayList()
        temp.clear()
        if((displayListRoom==null ||displayListRoom.size==0)&& numToast<2){
            Toast.makeText(context,"No offline data found",Toast.LENGTH_SHORT).show()
            numToast++
        }
        for (d in displayListRoom) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.trackName?.contains(data.toString(), ignoreCase = true)!!) {
                temp.add(d)
                Log.d("track NAME", "filter: " + d.trackName)
            }
        }

        if(displayListRoom!=null) {
            adapter = RepoListAdapter(displayList)
//               recyclerView.layoutManager = layoutManager
            val manager =
                GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
            recyclerView.layoutManager = manager
            recyclerView.itemAnimator = DefaultItemAnimator()
            recyclerView.adapter = adapter
        }

    }
    private fun getJsonData(data: String?) {
        val request = MusicService.buildService(ITunesApi::class.java)

        val db = MusicDatabase.getInstance(applicationContext)
        var musicDao = db?.musicDao()

//        val call = request.getJsonFile("#justin beiber");
        if (data != null) {
            val call = request.getJsonFile(data);
            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    val response = response.body()!!.string()

                    val json_contact: JSONObject = JSONObject(response)
                    var jsonarray_results: JSONArray = json_contact.getJSONArray("results")
                    var size: Int = jsonarray_results.length()
                    itemsList = ArrayList();

                    for (i in 0..size - 1) {
                        var json_objectdetail: JSONObject = jsonarray_results.getJSONObject(i)
//                        var wrapperType = json_objectdetail.getString("wrapperType")
//
//                        var trackName = json_objectdetail.getString("trackName")

//                        Log.d("wr", "onResponse: " + wrapperType)
//                   ,json_objectdetail.getString("collectionArtistViewUrl")
//                   var resultsDataRoom: ResultsOff = ResultsOff(
//                       json_objectdetail.getString("wrapperType"),
//                       json_objectdetail.getString(
//                           "kind"
//                       ),
//                       json_objectdetail.getInt("collectionId"),
//                       json_objectdetail.getInt("trackId"),
//                       json_objectdetail.getString(
//                           "artistName"
//                       ),
//                       json_objectdetail.getString("collectionName"),
//                       json_objectdetail.getString("trackName"),
//                       json_objectdetail.getString(
//                           "collectionCensoredName"
//                       ),
//                       json_objectdetail.getString("trackCensoredName"),
//                       json_objectdetail.getString("collectionViewUrl"),
//                       json_objectdetail.getString(
//                           "previewUrl"
//                       ),
//                       json_objectdetail.getString("trackPrice"),
//                       json_objectdetail.getString("releaseDate"),
//                       json_objectdetail.getString(
//                           "country"
//                       ),
//                       json_objectdetail.getString("primaryGenreName"),
//                       json_objectdetail.getString("artworkUrl100")
//                   )
//                   roomList.add(resultsDataRoom)
                        var wrapperType = "null"
                        var kind = "null"
                        var trackId = 0
                        var artistName = "null"
                        var collectionName = "null"
                        var trackName = "null"
                        var collectionCensoredName = "null"
                        var trackCensoredName = "null"
                        var collectionViewUrl = "null"
                        var previewUrl = "null"
                        var trackPrice = "null"
                        var releaseDate = "null"
                        var country = "null"
                        var collectionId = 0
                        var primaryGenreName = "null"
                        var artworkUrl100 = "null"
                        if (json_objectdetail.has("wrapperType")) {
                            wrapperType = json_objectdetail.getString("wrapperType")
                        }
                        if (json_objectdetail.has("kind")) {
                            kind = json_objectdetail.getString("kind")
                        }
                        if (json_objectdetail.has("collectionId")) {
                            collectionId = json_objectdetail.getInt("collectionId")
                        }
                        if (json_objectdetail.has("trackId")) {
                            trackId = json_objectdetail.getInt("trackId")
                        }
                        if (json_objectdetail.has("artistName")) {
                            artistName = json_objectdetail.getString("artistName")
                        }
                        if (json_objectdetail.has("collectionName")) {
                            collectionName = json_objectdetail.getString("collectionName")
                        }
                        if (json_objectdetail.has("trackName")) {
                            trackName = json_objectdetail.getString("trackName")
                        }
                        if (json_objectdetail.has("collectionCensoredName")) {
                            collectionCensoredName =
                                json_objectdetail.getString("collectionCensoredName")
                        }
                        if (json_objectdetail.has("trackCensoredName")) {
                            trackCensoredName = json_objectdetail.getString("trackCensoredName")
                        }
                        if (json_objectdetail.has("collectionViewUrl")) {
                            collectionViewUrl = json_objectdetail.getString("collectionViewUrl")
                        }
                        if (json_objectdetail.has("previewUrl")) {
                            previewUrl = json_objectdetail.getString("previewUrl")
                        }
                        if (json_objectdetail.has("trackPrice")) {
                            trackPrice = json_objectdetail.getString("trackPrice")
                        }
                        if (json_objectdetail.has("releaseDate")) {
                            releaseDate = json_objectdetail.getString("releaseDate")
                        }
                        if (json_objectdetail.has("country")) {
                            country = json_objectdetail.getString("country")
                        }
                        if (json_objectdetail.has("primaryGenreName")) {
                            primaryGenreName = json_objectdetail.getString("primaryGenreName")
                        }


                        if (json_objectdetail.has("artworkUrl100")) {
                            artworkUrl100 = json_objectdetail.getString("artworkUrl100")
                        }

                        val resultData: Results = Results(
                            0,
                            wrapperType,
                            kind,
                            collectionId,
                            trackId,
                            artistName,
                            collectionName,
                            trackName,
                            collectionCensoredName,
                            trackCensoredName,
                            collectionViewUrl,
                            previewUrl,
                            trackPrice,
                            releaseDate,
                            country,
                            primaryGenreName,
                            artworkUrl100
                        )


//                        var resultData: Results = Results(
//                            0,
//                            json_objectdetail.getString("wrapperType"),
//                            json_objectdetail.getString(
//                                "kind"
//                            ),
//                            json_objectdetail.getInt("collectionId"),
//                            json_objectdetail.getInt("trackId"),
//                            json_objectdetail.getString(
//                                "artistName"
//                            ),
//                            json_objectdetail.getString("collectionName"),
//                            json_objectdetail.getString("trackName"),
//                            json_objectdetail.getString(
//                                "collectionCensoredName"
//                            ),
//                            json_objectdetail.getString("trackCensoredName"),
//                            json_objectdetail.getString("collectionViewUrl"),
//                            json_objectdetail.getString(
//                                "previewUrl"
//                            ),
//                            json_objectdetail.getString("trackPrice"),
//                            json_objectdetail.getString("releaseDate"),
//                            json_objectdetail.getString(
//                                "country"
//                            ),
//                            json_objectdetail.getString("primaryGenreName"),
//                            json_objectdetail.getString("artworkUrl100")
//                        )
                        val resultOffData = resultData.toRoomRecord()
                        musicViewModel.insertData(applicationContext, resultOffData)

                        itemsList.add(resultData)
                        roomList.add(resultOffData.toResponseRecord())
                        //arrayListSearch.add(Results(json_objectdetail.getString("trackName")))
//                   model.id=json_objectdetail.getString("id")
//                   model.name=json_objectdetail.getString("name")
//                   model.email=json_objectdetail.getString("email")
//                   arrayList_details.add(model)
                    }
                    displayList.addAll(itemsList)
                    displayListRoom.addAll(itemsList)

//               val layoutManager = LinearLayoutManager(applicationContext)
                    adapter = RepoListAdapter(itemsList)
//               recyclerView.layoutManager = layoutManager
                    val manager =
                        GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
                    recyclerView.layoutManager = manager
                    recyclerView.itemAnimator = DefaultItemAnimator()
                    recyclerView.adapter = adapter


                    musicViewModel.getLabelsDetails(context).observe(
                        this@MainActivity,
                        androidx.lifecycle.Observer {

                            GlobalScope.launch {
                                for (i in 0..it.size-1) {
                                    var j = it.get(i)
                                    Log.d("room data new", "onResponse: " + j)
                                }
                            }
                        })


//                    GlobalScope.launch {
//                        for (i in 0..roomList.size - 1) {
//                            var j = roomList[i]
//                            musicDao?.insertAll(j)
//
//                        }


                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    filterRoom("")
                }

            })
        }
    }

    private fun isNetworkConnected(): Boolean {
        //1
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        //2
        val activeNetwork = connectivityManager.activeNetwork
        //3
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        //4
        return networkCapabilities != null &&
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    fun filter(text: String?) {
        Log.d("text rec", "filter: " + text)
        val temp: ArrayList<Results> = ArrayList()
        temp.clear()
        for (d in displayList) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.trackName?.contains(text.toString(), ignoreCase = true)!!) {
                temp.add(d)
                Log.d("track NAME", "filter: " + d.trackName)
            }
        }
        //update recyclerview
//        val layoutManager = LinearLayoutManager(applicationContext)
        adapter = RepoListAdapter(temp)
//        recyclerView.layoutManager = layoutManager
        val manager = GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = manager

        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }

    fun filterRoom(text: String?) {
        Log.d("text rec", "filter: " + text)
        val temp: ArrayList<Results> = ArrayList()
        temp.clear()
        for (d in displayListRoom) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (d.trackName?.contains(text.toString(), ignoreCase = true)!!) {
                temp.add(d)
                Log.d("track NAME", "filter: " + d.trackName)
            }
        }
        //update recyclerview
//        val layoutManager = LinearLayoutManager(applicationContext)
        adapter = RepoListAdapter(temp)
//        recyclerView.layoutManager = layoutManager
        val manager = GridLayoutManager(applicationContext, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = manager

        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val menuItem = menu?.findItem(R.id.search)
        if (menuItem != null) {
            val searchView = menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(query: String?): Boolean {
//                    val search= query?.toLowerCase(Locale.getDefault())
//                    filter(search?.toLowerCase())
                    val search = query?.toLowerCase(Locale.getDefault())

                    if(!getConnectionType(applicationContext)){
                        filterRoom(search)
                    }
                    getJsonData(search)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    val search = newText?.toLowerCase(Locale.getDefault())

                    if(!getConnectionType(applicationContext)){
                        if (search != null) {
                            getOfflineData(search)
                        }
                    }

                    filter(search)
                    return true
                }


            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    private fun getConnectionType(context: Context): Boolean {
        var result = 0 // Returns connection type. 0: none; 1: mobile data; 2: wifi
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    if (hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = 2
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = 1
                    } else if (hasTransport(NetworkCapabilities.TRANSPORT_VPN)) {
                        result = 3
                    }
                }
            }
        }
        return result > 0
    }
}

