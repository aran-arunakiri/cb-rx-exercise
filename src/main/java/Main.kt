import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import java.util.*
import java.util.concurrent.TimeUnit

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val climateService = ClimateService()
        val minutelyTrigger = Observable.interval(1, TimeUnit.SECONDS).map { Unit }

        //todo: we want to combine the individual streams of the climate service into a CombinedMeasurement
        //todo: we are only interested in accurate measurements, we also need to transform the temperatures to Fahrenheit
        //todo: each stream has an emission interval < 1 minute, but we want to query the API each minute, we use the average of measurements within that minute to query the API

        val accurateTemperatureInFahrenheit = TODO()
        val accurateCarbon = TODO()
        val accurateTraffic = TODO()

        val combinedMeasurement = TODO()
        val minutelyAverage = TODO()

        //We want to submit these measurement to an API which returns a DrivingState (indicates whether driving is allowed)
        makeAPICallToDetermineDrivingState(minutelyAverage).doOnNext { println("isDrivingAllowed ? ${it.drivingAllowed}") }.subscribe({}, {}, { println("completed") })

        while (true) {
            Thread.sleep(100)
        }
    }

    //todo: map temperature from CelsiusTemperature to FahrenheitTemperature
    private fun celsiusToFahrenheit(temperatureInCelsius: Observable<CelsiusTemperature>): Observable<FahrenheitTemperature> {
        TODO()
    }

    //todo: only emit accurate measurements
    private fun <T : Quantity> accurateMeasurements(measurements: Observable<T>): Observable<T> {
        TODO()
    }

    //todo: combine temperature & carbon  & traffic into a CombinedMeasurement
    //todo: each stream emits at the same pace, and each accurate emission can be considered chronologically in sync with other accurate emissions.
    private fun combineMeasurements(temperatureFahrenheit: Observable<FahrenheitTemperature>, carbon: Observable<Carbon>, traffic: Observable<Traffic>): Observable<CombinedMeasurement> {
        TODO()
    }

    //todo: average for given period
    private fun periodicMeasurements(measurements: Observable<CombinedMeasurement>, timeWindowPassed: Observable<Unit>): Flowable<AverageCombinedMeasurements> {
        TODO()
    }

    //todo: query the API for a drivingstate, sometimes the response of a call take very long, we want to make sure we always emit the latest available drivingState
    private fun makeAPICallToDetermineDrivingState(measurements: Flowable<AverageCombinedMeasurements>): Flowable<DrivingState> {
        TODO()
    }

}
