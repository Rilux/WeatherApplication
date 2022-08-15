package com.example.weather.data

data class BasicApiLoginData(
    var cityName: String = "Kharkiv",
    var units: Boolean = true,
    var cityApiKey: String = "323903"
) {
    companion object {
        @JvmStatic
        val apiCode: String = "4d88423d5140e8d43436999c139cbf8d"
    }
}
