import { getCities, fetchCoordinates } from "./cities/cityService.js";
import { fetchWeather } from "./weatherService.js";

const URL_ICON = "https://openweathermap.org/img/wn/{iconValue}@2x.png";

document.addEventListener("DOMContentLoaded", () => {
    const cities = getCities();
    const select = document.getElementById("city");

    loadCities(cities, select);

    const form = document.getElementById("myForm");
    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        const citySelected = select.value;

        const coordinates = await fetchCoordinates(citySelected);
        const { lon, lat } = coordinates;

        const weather = await fetchWeather(lon, lat);
        createElement(weather);
    });

    function loadCities(citiesArray, select) {
        citiesArray.forEach(city => {
            const option = document.createElement("option");
            option.value = city;
            option.text = city;
            select.appendChild(option);
        });
    }

    function createElement(weatherData) {
        const container = document.getElementById("container");
        container.innerHTML = "";

        // Obtenemos las entradas del objeto (pares clave-valor)
        const entries = Object.entries(weatherData);

        // Usamos un bucle for...of clásico que es más fácil de leer
        for (const entry of entries) {
            const key = entry[0];   // La clave (ej: "city")
            const value = entry[1]; // El valor (ej: "Madrid")

            const li = document.createElement("li");

            // Aquí puedes poner "opciones" o condiciones para tratar cada dato de forma diferente
            if (key === "iconUrl") {
                // Si es la imagen, creamos una etiqueta img
                const img = document.createElement("img");
                img.src = getIcon(value);
                img.alt = "Weather icon";
                li.appendChild(img);
            } else if (key === "temperature") {
                // Si es la temperatura, le añadimos el símbolo de grados
                li.textContent = `${key}: ${value} °C`;
            } else {
                // Para el resto (ciudad, descripción), lo mostramos normal
                li.textContent = `${key}: ${value}`;
            }

            container.appendChild(li);
        }
    }

    function getIcon(value){
        const iconSrc = URL_ICON.replace("{iconValue}", value);

        return iconSrc;
    }
});