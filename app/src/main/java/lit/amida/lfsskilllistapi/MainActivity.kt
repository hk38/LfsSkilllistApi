package lit.amida.lfsskilllistapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import coil.load
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        val userService = retrofit.create(UserService::class.java)

        button.setOnClickListener {
            runBlocking(Dispatchers.IO){
                kotlin.runCatching { userService.getUser("lifeistech") }
            }.onSuccess {
                imageView.load(it.icon)
                textName.text = it.name
                textId.text = it.id
                textFollowing.text = "Following:${it.following}"
                textFollower.text = "Follower:${it.followers}"
            }.onFailure {
                Toast.makeText(this, "failure", Toast.LENGTH_LONG).show()
            }
        }
    }
}