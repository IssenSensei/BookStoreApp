package com.example.bookstoreapp.database

import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL

/**
 * Created by Belal on 9/9/2017.
 */
class DownloadFileAsync(path: String) : AsyncTask<String, String, Array<String?>>() {
    var current = 0
    private var path: String = path

    lateinit var fpath: String
    var show = false


    protected override fun onPreExecute() {
        super.onPreExecute()
    }

    protected override fun doInBackground(vararg aurl: String): Array<String?> {
        val rows = aurl.size
        while (current < rows) {
            var count: Int
            try {
                println("Current: " + current + "\t\tRows: " + rows)
                fpath = getFileName(this.path)
                val url = URL(this.path)
                val connection = url.openConnection()
                connection.connect()
                val lenghtOfFile = connection.contentLength
                val input = BufferedInputStream(url.openStream(), 512)
                val file = File(Environment.getExternalStorageDirectory().path.plus(File.separator).plus(fpath))
                if (!file.exists()) file.createNewFile()
                val output = FileOutputStream(file)
                val data = ByteArray(512)
                var total: Long = 0
                while (true) {
                    count = input.read(data)
                    if (count == -1) break
                    total += count
                    output.write(data, 0, count)
                }


                show = true
                output.flush()
                output.close()
                input.close()
                current++
            } catch (e: Exception) {
                Log.d("Exception", "" + e)
            }
        } // while end
        return downPaths
    }

    override fun onProgressUpdate(progress: Array<String?>) {

    }

    override fun onPostExecute(result: Array<String?>) {
    }

    private fun getFileName(wholePath: String): String {
        var name: String? = null
        val start: Int
        val end: Int
        start = wholePath.lastIndexOf('/')
        end = wholePath.length //lastIndexOf('.');
        name = wholePath.substring((start + 1), end)
        return name
    }
}