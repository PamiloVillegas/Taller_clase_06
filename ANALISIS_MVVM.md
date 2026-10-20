**Antes de empezar: predicción (obligatorio)**

Escribe tu predicción **antes** de programar:
Si tu `ViewModel` guarda la lista en una variable normal (`var cursosCache = listOf(...)`) además de en el `MutableStateFlow`, y actualizas solo la variable pero no el `StateFlow`, ¿la UI reflejará el cambio? Justifica tu respuesta.

> **Predicción inicial** No, la interfaz de usuario no reflejará el cambio si se actualiza únicamente la variable ´cursosCache y no se modifica el ´MutableStateFlow´. La variable ´cursosCache´ es una variable normal del ´ViewModel´ y Compose no observa automáticamente sus cambios. En cambio, la interfaz obtiene la lista mediante el ´StateFlow´, que es el estado observable que estamos utilizando para comunicar los datos a la pantalla.

**Checkpoint 2.2 — Diagnóstico del bug de estado**

> El problema ocurre porque Compose observa los cambios del ´StateFlow´ expuesto por el ´ViewModel´, en nuestro caso ´elementos´, mediante ´collectAsStateWithLifecycle()´. Cuando se ejecuta la función ´marcarFavorito(id)´, se modifica la variable ´elementosCache´, que es una propiedad normal de la clase y no un estado observable por Compose. Aunque la lista se actualiza correctamente dentro de esa variable, el valor de ´_elementos´ permanece igual.

> Como la interfaz está suscrita a ´melementos´ y no a ´elementosCache´, Compose no recibe una notificación del cambio y, por tanto, no actualiza visualmente el favorito.
> ¨Compose observa el ´StateFlow´, no las variables arbitrarias de la clase¨. Para que la interfaz reaccione, el cambio debe reflejarse en el estado observable que consume la pantalla.

**Parte 3 — Persistencia del estado ante cambios de configuración**

Se marcaron varias películas como favoritas y posteriormente se rotó el dispositivo Motorola de orientación vertical a horizontal.
**Resultado:** las películas conservaron sus favoritos después de la rotación.

_Análisis_

> El resultado se explica porque el estado de las películas se administra mediante un ´ViewModel´. Android conserva normalmente la instancia del ´ViewModel´ cuando recrea la actividad debido a un cambio de configuración, como la rotación de la pantalla.
> La lista de películas se mantiene en el ´StateFlow_elementos´, por lo que la interfaz puede volver a observar el estado existente después de la recreación.
> Este comportamiento es diferente del estado administrado únicamente con ´remember´, que no garantiza conservar su valor cuando se destruye y reconstruye la composición.
