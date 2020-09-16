package lit.amida.lfsskilllistapi

import com.google.gson.annotations.SerializedName

data class User(
    val name: String,
    @SerializedName("login") val id: String,
    @SerializedName("avatar_url") val icon: String,
    val following: Int,
    val followers: Int
)