package com.benhurqs.test.data

data class CarResponse(
    val cars: List<Car>? = null
)

data class Car(
    val id: Int? = null,
    val timestamp_cadastro: Long? = null,
    val modelo_id: Int? = null,
    val ano: Int? = null,
    val combustivel: String? = null,
    val num_portas: Int? = null,
    val cor: String? = null,
    val nome_modelo: String? = null,
    val valor: Double? = null
)