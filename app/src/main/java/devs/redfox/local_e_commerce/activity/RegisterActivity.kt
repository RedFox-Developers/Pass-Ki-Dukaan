package devs.redfox.local_e_commerce.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devs.redfox.local_e_commerce.MainActivity
import devs.redfox.local_e_commerce.R
import devs.redfox.local_e_commerce.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button4.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.btnRegister.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

//    private fun validateUser() {
//        if (binding.userName.text!!.isEmpty() || binding.userNumber.text!!.isEmpty()) {
//            Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show()
//        } else {
//            storeData()
//        }
//    }

//    private fun storeData() {
//        val builder = AlertDialog.Builder(this)
//            .setTitle("Loading....")
//            .setMessage("Please Wait")
//            .setCancelable(false)
//            .create()
//        builder.show()
//
//        val data = hashMapOf<String, Any>()
//        data["name"] = binding.userName.text.toString()
//        data["number"] = binding.userNumber.text.toString()
//
//        Firebase.firestore.collection("users").document(binding.userNumber.text.toString())
//            .set(data).addOnSuccessListener {
//                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
//                builder.dismiss()
//                openLogin()
//            }
//            .addOnFailureListener {
//                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
//                builder.dismiss()
//            }
//    }

    private fun openLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}