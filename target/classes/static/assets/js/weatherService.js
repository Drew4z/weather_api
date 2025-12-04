import {getWeather} from "./api/api.js"

export async function fetchWeather(lon, lat){
    const data = await getWeather(lon,lat);
    return data;
}