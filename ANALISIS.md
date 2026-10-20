**Antes de empezar: predicción (obligatorio)**
Escribe tu predicción **antes** de codificar:
Si el usuario toca la MISMA tarjeta tres veces rápido antes de que cargue la pantalla de detalle, ¿cuántas veces se navegará y cuántas pantallas de detalle quedarán apiladas?

> _Rta/_ Deberia abrir una sola vez el detalle de la tarjeta seleccionada, sin embargo es posible que si se tocan 2 o mas tarjetas antes de que abra la primera, en pila deberian quedar los detalles de las tarjetas seleccionadas.

**Paso 3.2 — Diagnostica**
**Qué hacer:** explica por qué ocurre. Pista: cada llamada a `navigate()` **apila** un destino nuevo, sin verificar si ya está en el tope.

> Como indica la pista; por ejemplo, cuando se da ´click´ repetido en el ´elemento´ $5, se ejecuta ´navController.navigate("detalle/5")´ aunque el ´detalle´ seleccionado es igual, ´navigate()´ interpreta que se quiere agrgar un nuevo destino y son 3 entradas diferentes en el ´backStack´, asi que al pulsar ´volver´ y ejecutar ´navController.popBackStack´ solo se elimina una y muestra la que sigue en ´pila´.
> Esto sucede por que no hay un mecanismo que controle que el mismo destino no se agregue a la lista si este ya existe.
