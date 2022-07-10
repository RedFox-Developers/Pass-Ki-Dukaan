package devs.redfox.local_e_commerce.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import devs.redfox.local_e_commerce.MainActivity
import devs.redfox.local_e_commerce.R
import devs.redfox.local_e_commerce.databinding.ActivityOtpactivityBinding

class OTPActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVerifyOTP.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}