package hu.homework.bme.mrdeakd.nytimesmostpopular

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import android.content.Intent
import android.net.Uri
import android.support.design.widget.Snackbar


class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val extras = intent.extras

        if (extras != null) {
            useExtrasToSetValues(extras)
            if(NetworkAvailable(this,cL_layout)){
                b_opencrome.setOnClickListener{
                    openCromeFromURL(extras)
                }
            }
            else{
                b_opencrome.setOnClickListener{
                    makeSnackbarNoConnection()
                }
            }

        }
    }

    private fun useExtrasToSetValues(extras: Bundle) {
        tv_by.text = extras.getString("byline")
        tv_date.text = extras.getString("published_date")
        tv_title.text = extras.getString("title")
        Picasso.get().load(extras.getString("largepic")).into(iv_imageofarticle)
    }

    private fun makeSnackbarNoConnection() {
        Snackbar.make(
            cL_layout,
            "Nincs internet, ezért nem lehet megnyitni a linket!",
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun openCromeFromURL(extras: Bundle) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(extras.getString("url"))
        intent.setPackage("com.android.chrome")
        startActivity(intent)
    }
}
