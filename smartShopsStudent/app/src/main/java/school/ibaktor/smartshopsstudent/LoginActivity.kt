package school.ibaktor.smartshopsstudent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsername : EditText
    private lateinit var etPassword : EditText
    private lateinit var btLogin : Button
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btLogin = findViewById(R.id.btLogin)


        setupListeners()
    }

    fun setupListeners(){
        btLogin.setOnClickListener{
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            login(username, password)

        }
    }

    fun login(username: String, password: String){

        firebaseAuth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(this){task ->
                if ( task.isSuccessful ){
                    val usuarioActual = firebaseAuth.currentUser
                    val sharedPreferences = getSharedPreferences("PrefSmartShop", Context.MODE_PRIVATE)
                    val editorSharedPreferences = sharedPreferences.edit()
                    editorSharedPreferences.putString("auth_user", usuarioActual?.uid)
                    editorSharedPreferences.apply()

                    Toast.makeText(baseContext, "uid ${usuarioActual?.uid}", Toast.LENGTH_SHORT).show()

                    val mainIntent = Intent(this, MainActivity::class.java)
                    startActivity( mainIntent )
                    finish()
                } else {
                    Toast.makeText(baseContext, "Usuario o contraseña invalida", Toast.LENGTH_SHORT).show()
                }
            }

        /*if( username == "user" && password == "pass" ){
            val token = "token_prueba_132176312876387123"

            val sharedPreferences = getSharedPreferences("PrefSmartShop", Context.MODE_PRIVATE)
            val editorSharedPreferences = sharedPreferences.edit()
            editorSharedPreferences.putString("token", token)
            editorSharedPreferences.apply()

            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity( mainIntent )
            finish()
        }else{
            Toast.makeText(this, "Usuario y/o contraseña incorrecta", Toast.LENGTH_LONG).show()
        }*/
    }
}