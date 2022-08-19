package com.example.weather.data.model

data class DataForCoordinatesSearch(
    var latitude : String = "49.9935",
    var longitude : String = "36.2304",
    val count: String = "40",
    val units: String = "metric",
    var cityName: String = "Kharkiv",
    var cityApiKey: String = "323903",
    var country: String = "UA"
) {
    companion object {
        @JvmStatic
        val apiCode: String = "4d88423d5140e8d43436999c139cbf8d"
    }
}