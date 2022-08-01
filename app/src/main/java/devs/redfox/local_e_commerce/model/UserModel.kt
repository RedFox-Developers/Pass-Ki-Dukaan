package devs.redfox.local_e_commerce.model

data class UserModel(
    val userName:String? = "",
    val userPhoneNumber:String?="",
    val address:String?="",
    val state:String?="",
    val city:String?="",
    val pinCode:String?="",
)
