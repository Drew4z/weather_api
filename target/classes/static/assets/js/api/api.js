const URL_COORDINATES = "http://localhost:8081"
const URL_WEATHER = "http://localhost:8081"


export const getCoordinates = async (city) => {
    try {
        const response = await fetch(`${URL_COORDINATES}/api/v1/coordinates?city=${city}`)
        if (!response.ok) { throw new Error("City not found") }
        const data = await response.json()

        return data
    } catch (error) {
        console.log(error)
    }

}

export const getWeather = async (lon, lat) => {
    try {
        const response = await fetch(`${URL_WEATHER}/api/v1/weather?lon=${lon}&lat=${lat}`)
        if (!response.ok) { throw new Error("Weather not found") }
        const data = await response.json()

        return data
    } catch (error) {
        console.log(error)
    }

}