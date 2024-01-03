package `in`.gov.edudel.smcapp

import android.content.Context
import android.widget.Toast
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

object utils {

    const val API_PASSWORD  = "f#2T9x*cJP6@"
    const val API_ENCRYPTED_PASSWORD  = "DxoKT2jfA72VYDkdLO04PCknTUOn6MScw3HELpwha1U="
    private const val API_ENCRYPTION_KEY  = "cbQCoqwfdtjztPLfdPJu4zw5M50SFSvNFB1pzuLUmts="
    const val apiEmployeeDataUrl = "https://www.edudel.nic.in/libraryservice/LibraryAuth.asmx/AuthUserJSON"
    const val apiStudentDataUrl = "https://www.edudel.nic.in/libraryservice/LibraryAuth.asmx/GetStudentDetailsJSON"

    fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) = Toast.makeText(this, message, duration).show()

    fun encrypt(input: String): String{
        try {
            val inputBytes = input.toByteArray(Charsets.UTF_16LE)
            val encryptionKey = API_ENCRYPTION_KEY.toCharArray()
            val salt = "Ivan Medvedev".toByteArray(Charsets.UTF_8)

            val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            val spec = PBEKeySpec( encryptionKey, salt, 1000, 384)

            val secret = factory.generateSecret(spec)
            val secretKey = secret.encoded.slice(0..31).toByteArray()
            val initializationVector = secret.encoded.slice(32..47).toByteArray()

            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, SecretKeySpec(secretKey, "AES"), IvParameterSpec(initializationVector))
            val encryptedBytes = cipher.doFinal( inputBytes )

            return Base64.getEncoder().encodeToString(encryptedBytes)

        } catch (e: Exception) {
            e.printStackTrace()
            return ""
        }
    }

    fun randomOtp(): String {
        return ""
    }
}