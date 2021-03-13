package apps.appManageUsers.model

data class UserRegister(@JsName("firstnameUR") val firstnameUR : String,
                        @JsName("lasttnameUR") val lasttnameUR : String,
                        @JsName("usernameUR") val usernameUR : String,
                        @JsName("useremailUR") val useremailUR : String,
                        @JsName("userpasswordUR") val userpasswordUR :String,
                        @JsName("userrolesUR") val userrolesUR : Set<String>)
