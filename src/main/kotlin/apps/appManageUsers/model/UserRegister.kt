package apps.appManageUsers.model

data class UserRegister(val firstname : String,
                        val lasttname : String,
                        val username : String,
                        val useremail : String,
                        val userpassword :String,
                        val userroles : Set<String>)
