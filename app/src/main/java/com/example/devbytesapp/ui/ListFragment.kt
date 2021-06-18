package com.example.devbytesapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.devbytesapp.DevbytesAdapter
import com.example.devbytesapp.R
import com.example.devbytesapp.database.VideoDatabase.Companion.getInstance


class ListFragment : Fragment() {

    private lateinit var listFragmentViewModel: ListFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val databaseVideoDao = getInstance(application).databaseVideoDao
        val listFragmentViewModelFactory = ListFragmentViewModelFactory(application)
        listFragmentViewModel = ViewModelProvider(this,listFragmentViewModelFactory).get(ListFragmentViewModel::class.java)

        val adapter = DevbytesAdapter(DevbytesAdapter.DevBytesClick {
            val intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(it.url))
            intent.setPackage("com.google.android.youtube")
            startActivity(intent)
        })

        val recyclerView = view.findViewById<RecyclerView>(R.id.devbytes_recycler_view)
        recyclerView.adapter = adapter

        listFragmentViewModel.playlist.observe(viewLifecycleOwner, Observer {
            adapter.data = it
        })
    }
}