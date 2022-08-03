package devs.redfox.local_e_commerce.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import devs.redfox.local_e_commerce.databinding.ActivityAddressBinding


class AddressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddressBinding

    private lateinit var preferences: SharedPreferences

    private lateinit var totalCost: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddressBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferences = this.getSharedPreferences("user", MODE_PRIVATE)

        totalCost = intent.getStringExtra("totalCost")!!

        loadUserInfo()

        binding.btnProceed.setOnClickListener {
            validateData(
                binding.userNumber.text.toString(),
                binding.userName.text.toString(),
                binding.userAddress.text.toString(),
                binding.userCity.text.toString(),
                binding.userState.text.toString(),
                binding.userPinCode.text.toString()
            )
        }
    }

    private fun validateData(
        number: String,
        name: String,
        address: String,
        city: String,
        state: String,
        pinCode: String
    ) {
        if (number.isEmpty() || name.isEmpty() || address.isEmpty() || city.isEmpty() || state.isEmpty() || pinCode.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
        } else {
            storeData(address, city, state, pinCode)
        }
    }

    private fun storeData(
        address: String,
        city: String,
        state: String,
        pinCode: String
    ) {

        val map = hashMapOf<String, Any>()
        map["address"] = address
        map["city"] = city
        map["state"] = state
        map["pinCode"] = pinCode

        Firebase.firestore.collection("users")
            .document(preferences.getString("number", "")!!)
            .update(map).addOnSuccessListener {

                val b = Bundle()
                b.putStringArrayList("productIds", intent.getStringArrayListExtra("productIds"))
                b.putString("totalCost", totalCost)
                val intent = Intent(this, CheckoutActivity::class.java)
                intent.putExtras(b)
                startActivity(intent)
            }
            .addOnFailureListener {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
    }


    private fun loadUserInfo() {


        Firebase.firestore.collection("users").document(preferences.getString("number", "")!!)
            .get().addOnSuccessListener {
                binding.userName.setText(it.getString("userName"))
                binding.userNumber.setText(it.getString("userPhoneNumber"))
                binding.userAddress.setText(it.getString("address"))
                binding.userCity.setText(it.getString("city"))
                binding.userState.setText(it.getString("state"))
                binding.userPinCode.setText(it.getString("pinCode"))

            }
            .addOnFailureListener {

            }
    }


}