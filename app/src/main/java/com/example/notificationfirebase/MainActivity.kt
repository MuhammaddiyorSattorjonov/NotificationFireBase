package com.example.notificationfirebase



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.notificationfirebase.databinding.ActivityMainBinding
import com.example.notificationfirebase.models.Data
import com.example.notificationfirebase.models.MyResponse
import com.example.notificationfirebase.models.Sender
import com.example.notificationfirebase.retrofit.ApiClient
import com.example.notificationfirebase.retrofit.ApiService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getToken()

        val apiService =
            ApiClient.getRetrofit("https://fcm.googleapis.com").create(ApiService::class.java)

        binding.apply {
            btnSend.setOnClickListener {

                apiService.sendNotification(
                    Sender(
                        Data(
                            "1-emulatordan",
                            R.drawable.ic_launcher_background,
                            binding.edtMessage.text.toString(),
                            binding.edtTitle.text.toString(),
                            "2-emualotrga"
                        ),
                        "f6H23aWZR22PjeULtzzGq-:APA91bGT1kF1bpaW7BVAgwAOASyqcQqEO7_JBy7OQqY6p_k5FrEo9L8KXqsHBRJp1gzGA7rCc_t3ht_grOypyCEtcP72Yu4n6n-M4cWyRj6rTz_eQpjQLFyFaOtneHWsa4Zqy-wKsQ2X"
                    )
                ).enqueue(object : Callback<MyResponse> {
                    override fun onResponse(
                        call: Call<MyResponse>,
                        response: Response<MyResponse>,
                    ) {
                        Toast.makeText(this@MainActivity, "Responce bo'ldi", Toast.LENGTH_SHORT)
                            .show()
                        if (response.isSuccessful) {
                            Toast.makeText(this@MainActivity,
                                "Notification sent",
                                Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<MyResponse>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                    }
                })

            }
        }
    }

    private fun getToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d(TAG, "onCreate: token falled")
            }
            val token = task.result
            Log.d(TAG, token ?: "")
            binding.tvToken.text = token
            Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
        })
    }
}
