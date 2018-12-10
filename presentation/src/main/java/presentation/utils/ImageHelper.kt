package presentation.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import java.io.File


const val CAMERA_REQUEST_CODE = 123
const val GALERY_REQUEST_CODE = 123

const val CAMERA_FILE_NAME = "camera"
const val CAMERA_FILE_EXT = ".jpg"

fun startCamera(activity: Activity) {

    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

    if (intent.resolveActivity(activity.packageManager) != null) {
        val file = File(activity.getExternalFilesDir(null)?.absolutePath
                + CAMERA_FILE_NAME + CAMERA_FILE_EXT)
        if (file.exists()) {
            file.delete()
        }

        val fileUrl = FileProvider.getUriForFile(activity, "presentation.utils.GeneralFileProvider", file)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUrl)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        activity.startActivityForResult(intent, CAMERA_REQUEST_CODE)
    } else {
        //в телефоне нет приложения для камеры
    }

}

fun checkActivityResult(context: Context,
                     requestCode: Int, resultCode: Int, data: Intent?): File? {
    if (resultCode != Activity.RESULT_OK) {
        return null
    }
    when (requestCode) {
        CAMERA_REQUEST_CODE -> {

            val file = File(context.getExternalFilesDir(null)?.absolutePath
                    + CAMERA_FILE_NAME + CAMERA_FILE_EXT)
            if (file.exists()) {
                return file
            }
        }
    }
    return null
}

fun startGallery() {

}
