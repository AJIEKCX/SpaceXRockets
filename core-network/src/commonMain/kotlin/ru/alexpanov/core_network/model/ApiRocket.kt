package ru.alexpanov.core_network.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.serializers.LocalDateIso8601Serializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiRocket(
    val id: String,
    val name: String,
    @SerialName("cost_per_launch")
    val costPerLaunch: Long,
    @Serializable(with = LocalDateIso8601Serializer::class)
    @SerialName("first_flight")
    val firstFlight: LocalDate,
    val country: String,
    val height: ApiHeight,
    val diameter: ApiDiameter,
    val mass: ApiMass,
    @SerialName("payload_weights")
    val payloadWeights: List<ApiPayloadWeight>,
    @SerialName("first_stage")
    val firstStage: ApiFirstStage,
    @SerialName("second_stage")
    val secondStage: ApiSecondStage,
    @SerialName("flickr_images")
    val flickrImages: List<String>
)

@Serializable
data class ApiHeight(
    val meters: Double,
    val feet: Double
)

@Serializable
data class ApiDiameter(
    val meters: Double,
    val feet: Double
)

@Serializable
data class ApiMass(
    val kg: Int,
    val lb: Int
)

@Serializable
data class ApiPayloadWeight(
    val id: String,
    val name: String,
    val kg: Int,
    val lb: Int
)

@Serializable
data class ApiFirstStage(
    val engines: Int,
    @SerialName("fuel_amount_tons")
    val fuelAmountTons: Double,
    @SerialName("burn_time_sec")
    val burnTimeSec: Int? = null
)

@Serializable
data class ApiSecondStage(
    val engines: Int,
    @SerialName("fuel_amount_tons")
    val fuelAmountTons: Double,
    @SerialName("burn_time_sec")
    val burnTimeSec: Int? = null
)