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
            if (NetworkAvailable(this, cL_layout)) {
                b_opencrome.setOnClickListener {
                    openCromeFromURL(extras)
                }
            } else {
                b_opencrome.setOnClickListener {
                    makeSnackbarNoConnection()
                }
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this@DetailActivity.overridePendingTransition(
            R.anim.abc_fade_in,
            R.anim.abc_fade_out
        )
    }

    private fun useExtrasToSetValues(extras: Bundle) {
        tv_by.text = extras.getString(getString(R.string.byline))
        tv_date.text = extras.getString(getString(R.string.published_date))
        tv_title.text = extras.getString(getString(R.string.title))
        Picasso.get().load(extras.getString(getString(R.string.largepic))).into(iv_imageofarticle)
    }

    private fun makeSnackbarNoConnection() {
        Snackbar.make(
            cL_layout,
            getString(R.string.noconnectionopenlink),
            Snackbar.LENGTH_LONG
        ).show()
    }

    private fun openCromeFromURL(extras: Bundle) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(extras.getString(getString(R.string.url)))
        intent.setPackage(getString(R.string.com_adroid_chrome))
        startActivity(intent)
    }
}
