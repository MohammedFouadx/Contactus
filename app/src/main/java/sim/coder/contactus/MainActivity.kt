package sim.coder.contactus

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat



class MainActivity : AppCompatActivity() {

    lateinit var gitHubImageButton:ImageButton
    lateinit var mapImageButton:ImageButton
    lateinit var gmailImageButton:ImageButton
    lateinit var contactMeImageButton:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)







        gitHubImageButton = findViewById(R.id.gitHub)
        gitHubImageButton.setOnClickListener {
            openGitHub()
        }

        mapImageButton = findViewById(R.id.map)
        mapImageButton.setOnClickListener {
            openMap()
        }

        gmailImageButton=findViewById(R.id.gmail)
        gmailImageButton.setOnClickListener {
            sendEmail()
        }

        contactMeImageButton = findViewById(R.id.contact)
        contactMeImageButton.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_CONTACTS)
                    != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,Array(1){Manifest.permission.READ_CONTACTS},111)
            }
            else
                contactMeImageButton.isClickable = false

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==111 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            contactMeImageButton.isClickable = true
            contactMe()
        }



    }

    fun openGitHub() {
        val openIntent = Intent().apply {
            action =Intent.ACTION_VIEW
            data = Uri.parse("https://github.com/MohammedFouadx")
        }
        if (openIntent.resolveActivity(packageManager)!=null){
            startActivity(openIntent)
        }

    }

    fun openMap(){
        val openIntent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("google.streetview:cbll=46.414382,10.013988")

        }
        if (openIntent.resolveActivity(packageManager)!=null){
            startActivity(openIntent)
        }

    }

    fun sendEmail(){
        val sendIntent = Intent().apply {
            action= Intent.ACTION_SEND
            type = "text/plain"

        }
        if (sendIntent.resolveActivity(packageManager)!=null){
            startActivity(sendIntent)
        }
    }


    fun contactMe(){
        val intent = Intent(
                ContactsContract.Intents.SHOW_OR_CREATE_CONTACT,
                ContactsContract.Contacts.CONTENT_URI)
        intent.data = Uri.parse("tel:777128028")

        intent.putExtra(ContactsContract.Intents.Insert.NAME, "Mohammed Fouad")
//        intent.putExtra(ContactsContract.Intents.Insert.POSTAL,
//                "House Address, Street Name, State/Country")
        startActivity(intent)
        Toast.makeText(this, "Contact Saved Successfully", Toast.LENGTH_SHORT).show()

    }

}