package devs.redfox.local_e_commerce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import devs.redfox.local_e_commerce.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var i = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setStatusBarColor(this.getResources().getColor(R.color.colorSecondary))
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        val navController = navHostFragment!!.findNavController()

        val popupMenu = PopupMenu(this, null)
        popupMenu.inflate(R.menu.bottom_nav)
        binding.bottomBar.setupWithNavController(popupMenu.menu, navController)


        binding.bottomBar.onItemSelected = {
            when (it) {
                0 -> {
                    i = 0;
                    navController.navigate(R.id.homeFragment)
                }
                1 -> {
                    i = 1;
                    navController.navigate(R.id.cartFragment)
                }
                2 -> {
                    i = 2;
                    navController.navigate(R.id.moreFragment)
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (i == 0) {
            finish()
        }
    }

}


