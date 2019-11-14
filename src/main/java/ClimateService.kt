import io.reactivex.Observable
import io.reactivex.Single
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class ClimateService {
    private val intervalInSeconds = 1L
    val temperatureObservable: Observable<CelsiusTemperature> = Observable
            .interval(intervalInSeconds, TimeUnit.SECONDS)
            .map { Random.nextInt(0, 35) }
            .map { CelsiusTemperature(it, Random.nextBoolean()) }

    val carbonObservable: Observable<Carbon> = Observable
            .interval(intervalInSeconds, TimeUnit.SECONDS)
            .map { Random.nextInt(300, 500) }
            .map { Carbon(it, Random.nextBoolean()) }

    val trafficObservable: Observable<Traffic> = Observable
            .interval(intervalInSeconds, TimeUnit.SECONDS)
            .map { Random.nextInt(0, 100) }
            .map { Traffic(it, Random.nextBoolean()) }
}

class CelsiusTemperature(val temperatureInCelsius: Int, isAccurate: Boolean) : Quantity(isAccurate)
class FahrenheitTemperature(val temperatureInFahrenheit: Int, isAccurate: Boolean) : Quantity(isAccurate)
class Carbon(val carbonInPPM: Int, isAccurate: Boolean) : Quantity(isAccurate)
class Traffic(val traffic: Int, isAccurate: Boolean) : Quantity(isAccurate)
open class Quantity(val isAccurate: Boolean)


class CombinedMeasurement(val celsiusTemperature: FahrenheitTemperature, val carbon: Carbon, val traffic: Traffic, val dateOfCombinedMeasurement: Date)

class AverageCombinedMeasurements() {
    private val measurements = mutableListOf<CombinedMeasurement>()
    private var temperatureSum = 0
    private var carbonSum = 0
    private var trafficSum = 0

    fun plusOne(measurement: CombinedMeasurement): AverageCombinedMeasurements {
        measurements.add(measurement)
        temperatureSum += measurement.celsiusTemperature.temperatureInFahrenheit
        carbonSum += measurement.carbon.carbonInPPM
        trafficSum += measurement.traffic.traffic
        return this
    }

    fun averageTemperature() = temperatureSum / measurements.size
    fun averageCarbon() = carbonSum / measurements.size
    fun averageTraffic() = trafficSum / measurements.size
}

class DrivingState(val drivingAllowed: Boolean)
class APIRequest(val measurement: AverageCombinedMeasurements) {
    fun execute(): Single<DrivingState> {
        return Single.just(DrivingState(Random.nextBoolean()))
    }
}