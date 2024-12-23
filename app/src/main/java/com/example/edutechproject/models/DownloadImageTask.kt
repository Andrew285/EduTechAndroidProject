import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import java.net.HttpURLConnection
import java.net.URL

class DownloadImagesTask(
    private val context: Context,
    private val imageView: ImageView,
    private val progressBar: ProgressBar
) : AsyncTask<String, Int, Bitmap?>() {

    override fun onPreExecute() {
        progressBar.visibility = View.VISIBLE
    }

    override fun doInBackground(vararg urls: String): Bitmap? {
        try {
            val imageUrl = URL(urls[0])
            val connection = imageUrl.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()

            val input = connection.inputStream
            return BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun onPostExecute(result: Bitmap?) {
        progressBar.visibility = View.GONE
        if (result != null) {
            imageView.setImageBitmap(result)
        } else {
            Toast.makeText(context, "Failed to download image", Toast.LENGTH_SHORT).show()
        }
    }
}
