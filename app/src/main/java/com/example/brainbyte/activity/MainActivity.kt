package com.example.brainbyte.activity

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.brainbyte.*
import com.example.brainbyte.databinding.ActivityMainBinding
import com.example.brainbyte.fragment.*


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var linkClickReceiver: LinkClickReceiver
    private val recentFragment = RecentFragment()
    private val contactFragment = ContactFragment()
    private val urlFragment = UrlFragment()
    private val upiFragment = UpiFragment()
    private val messageFragment = MessageFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        if(ContextCompat.checkSelfPermission(this,android.Manifest.
            permission.READ_PHONE_STATE) +
            ContextCompat.checkSelfPermission(this,android.Manifest.
            permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.READ_PHONE_STATE,android.Manifest.permission.READ_CALL_LOG),369
            )
        }
        linkClickReceiver = LinkClickReceiver()
        val intentFilter = IntentFilter(Intent.ACTION_VIEW)
        intentFilter.addDataScheme("http")
        intentFilter.addDataScheme("https")
        intentFilter.addCategory(Intent.CATEGORY_BROWSABLE)
        registerReceiver(linkClickReceiver,intentFilter)

        if(intent?.action == Intent.ACTION_VIEW){
            val url = intent.data?.toString()
            if(url != null){
                val args = Bundle()
                args.putString("Url",url)
                urlFragment.arguments = args
                loadFrag(urlFragment)
            }
        }
        loadFrag(urlFragment)
        binding?.bnView?.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.nav_recents -> loadFrag(recentFragment)
                R.id.nav_contacts -> loadFrag(contactFragment)
                R.id.nav_url -> loadFrag(urlFragment)
                R.id.nav_message -> loadFrag(messageFragment)
                R.id.nav_upi -> loadFrag(upiFragment)
            }
            true
        }

    }
    private fun  loadFrag(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
        unregisterReceiver(linkClickReceiver)
    }

    private inner class LinkClickReceiver: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Toast.makeText(this@MainActivity,"Link clicked",Toast.LENGTH_SHORT).show()
            val url = intent?.data?.toString()
            if(url != null){
                val mainActivityIntent = Intent(context,MainActivity::class.java)
                mainActivityIntent.putExtra("Url",url)
                mainActivityIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                mainActivityIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                mainActivityIntent.flags = Intent.FLAG_ACTIVITY_TASK_ON_HOME
                context?.startActivity(mainActivityIntent)
            }
        }
    }
}