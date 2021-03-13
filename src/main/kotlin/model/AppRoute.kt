package model

data class AppRoute(@JsName("nameAppAR") val nameAppAR : String,
                    @JsName("urlAR") val urlAR : String,
                    @JsName("descriptionLinkAR") val descriptionLinkAR: String,
                    @JsName("classesLinkAR") val classesLinkAR : String,
                    @JsName("tittleAR") val tittleAR : String,
                    @JsName("activeAR") val activeAR : Boolean) {
}

