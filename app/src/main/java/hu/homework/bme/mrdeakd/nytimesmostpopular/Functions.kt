package hu.homework.bme.mrdeakd.nytimesmostpopular

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.design.widget.Snackbar
import android.view.View

fun networkAvailable(context: Context, view: View) : Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
    val internet: Boolean
    internet = if (connectivityManager is ConnectivityManager) {
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        networkInfo?.isConnected ?: false
    } else
        false
    if (!internet)
        makeSnackbar(view, context)
    return internet
}

private fun makeSnackbar(view: View, context: Context) {
    Snackbar.make(
        view,
        context.getString(R.string.NoConnection),
        Snackbar.LENGTH_LONG
    ).show()
}