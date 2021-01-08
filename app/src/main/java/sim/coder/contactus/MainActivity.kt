package sim.coder.contactus

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    lateinit var gitHubImageButton:ImageButton
    lateinit var MapImageButton:ImageButton
    lateinit var GmailImageButton:ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gitHubImageButton = findViewById(R.id.gitHub)
        gitHubImageButton.setOnClickListener {
            openGitHub()
        }

        MapImageButton = findViewById(R.id.map)
        MapImageButton.setOnClickListener {
            openMap()
        }

        GmailImageButton=findViewById(R.id.gmail)
        GmailImageButton.setOnClickListener {
            sendEmail()
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

}