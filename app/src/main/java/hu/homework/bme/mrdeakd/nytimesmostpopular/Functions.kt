package hu.homework.bme.mrdeakd.nytimesmostpopular

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.design.widget.Snackbar
import android.view.View

fun NetworkAvailable(context: Context, view: View) : Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
    val internet: Boolean
    internet = if (connectivityManager is ConnectivityManager) {
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        networkInfo?.isConnected ?: false
    } else
        false
    if (!internet)
        Snackbar.make(
            view,
            context.getString(R.string.NoConnection),
            Snackbar.LENGTH_LONG
        ).show()
    return internet
}