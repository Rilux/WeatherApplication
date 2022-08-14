package com.example.weather.data

data class BasicApiLoginData(
    var cityName: String = "Kharkiv",
    var units: String = "metric",
    var iso3: String = "UAH"
) {
    companion object {
        @JvmStatic
        val apiCode: String = "4d88423d5140e8d43436999c139cbf8d"
    }
}
