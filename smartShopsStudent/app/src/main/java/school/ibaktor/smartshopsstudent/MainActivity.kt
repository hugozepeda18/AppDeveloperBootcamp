package school.ibaktor.smartshopsstudent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import school.ibaktor.smartshopsstudent.ui.fragment.AccountFragment
import school.ibaktor.smartshopsstudent.ui.fragment.HomeFragment
import school.ibaktor.smartshopsstudent.ui.fragment.ProductsFragment

class MainActivity : AppCompatActivity() {

    //Se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp( applicationContext )


        val sharedPreferences = getSharedPreferences("PrefSmartShop", Context.MODE_PRIVATE)

        //val editor = sharedPreferences.edit()
        //editor.putString("auth_user", "")
        //editor.apply()

        val token = sharedPreferences.getString("auth_user", "")

        if( token.isNullOrEmpty() ) {
            val loginIntent = Intent(this, LoginActivity::class.java)
            startActivity(loginIntent)
            finish()
        }

        val bottomNavigationViewMenu : BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationViewMenu.setOnItemSelectedListener { item ->
            var selectedFragment : Fragment? = null
            when( item.itemId ){
                R.id.menu_home -> {
                    selectedFragment = HomeFragment()
                }
                R.id.menu_products -> {
                    selectedFragment = ProductsFragment()
                }
                R.id.menu_profile -> {
                    selectedFragment = AccountFragment()
                }
            }
            selectedFragment?.let{
                supportFragmentManager.beginTransaction().replace(R.id.fragment_container_home, it).commit()
            }
            true
        }

        if(savedInstanceState == null){
            bottomNavigationViewMenu.selectedItemId = R.id.menu_home
        }
    }

    //Llamado cuando la actividad se vuelve visible para el usuario
    override fun onStart() {
        super.onStart()
    }

    //Llamado cuando la actividad comienza a interactuar con el usuario
    override fun onResume() {
        super.onResume()
    }

    //Llamado cuando el sistema está a punto de poner la actividad en segundo plano
    override fun onPause() {
        super.onPause()
    }

    //Llamado cuando la actividad ya no es visible para el usuario, porque está siendo destruida o porque otra actividad ha tomado el foco
    override fun onStop() {
        super.onStop()
    }

    //Llamado antes de que la actividad sea destruida. Esta es la última llamada que recibe la actividad antes de ser eliminada
    override fun onDestroy() {
        super.onDestroy()
    }

    //Llamado después de que la actividad haya sido detenida, justo antes de ser iniciada nuevamente
    override fun onRestart() {
        super.onRestart()
    }

}