import { getCoordinates } from "../api/api.js";

export function getCities() {
    return ["Barcelona", "Madrid", "Valencia", "Sevilla", "Valencia"];
}

export async function fetchCoordinates(citySelected){
    const data = await getCoordinates(citySelected);
    return data
}
