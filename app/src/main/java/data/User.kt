package data

import androidx.annotation.Nullable
import java.text.DateFormat

data class User(
    val id : Int?,
    var name : String,
    var email : String,
    var password : String,
    var age : Int,
    var portfolio : List<Charity>
)

