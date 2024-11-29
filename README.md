# Novelas5

He utilizado el repositorio de novelas4 para el de novelas5

Novelas5 es una aplicación de Android para gestionar y ver novelas. La aplicación permite a los usuarios registrarse, iniciar sesión, ver una lista de novelas, agregar reseñas y gestionar sus novelas favoritas. También incluye funciones para sincronizar datos con Firebase y manejar cambios de red.

## Clases y Métodos Importantes

### MainActivity
- **onCreate**: Inicializa la actividad, configura Firebase y configura el RecyclerView y los botones.
- **eliminarNovela**: Elimina una novela de la lista.
- **mostrarNotificacionFavorito**: Muestra una notificación cuando una novela se añade o se elimina de favoritos.
- **showNovelaDetails**: Abre los detalles de una novela seleccionada.

### LoginActivity
- **onCreate**: Inicializa la actividad y configura el botón de inicio de sesión para autenticar a los usuarios con Firebase.

### RegisterActivity
- **onCreate**: Inicializa la actividad y configura el botón de registro para crear nuevas cuentas de usuario con Firebase.

### AjustesActivity
- **onCreate**: Inicializa la actividad, configura los interruptores para el modo claro y oscuro, y un botón para volver a la pantalla principal.
- **updateNovelas**: Actualiza la lista de novelas en el RecyclerView.

### DetallesNovelaActivity
- **onCreate**: Inicializa la actividad y muestra los detalles de una novela seleccionada.

### FavoritosActivity
- **onCreate**: Inicializa la actividad, configura el RecyclerView para las novelas favoritas y un botón para volver a la pantalla principal.
- **eliminarNovela**: Elimina una novela de la lista de favoritos.
- **mostrarNotificacionFavorito**: Muestra una notificación cuando una novela se añade o se elimina de favoritos.

### AgregarResenaActivity
- **onCreate**: Inicializa la actividad, configura el spinner con la lista de novelas y un botón para guardar una reseña.

### DataSyncJobService
- **onStartJob**: Inicia la tarea de sincronización de datos en segundo plano.
- **onStopJob**: Maneja el evento de detención del trabajo.
- **DataSyncTask**: Una AsyncTask que realiza la sincronización de datos.

### DataSyncReceiver
- **onReceive**: Inicia el servicio de sincronización de datos cuando se detecta un cambio de red.

### NetworkChangeReceiver
- **onReceive**: Inicia el servicio de sincronización de datos cuando se detecta una conexión de red.

### FirebaseService
- **obtenerNovelas**: Recupera la lista de novelas desde Firebase.
- **buscarNovelaPorTitulo**: Busca una novela por título en Firebase.
- **compressData**: Comprime datos usando GZIP.
- **createRetrofitInstance**: Crea una instancia de Retrofit con caché.

### Novela
- **Implementación de Parcelable**: Permite que la clase Novela se pase entre actividades.
- **resenas**: Una lista de reseñas para la novela.

### NovelaAdaptador
- **onCreateViewHolder**: Crea view holders para el RecyclerView.
- **onBindViewHolder**: Vincula datos a los view holders.
- **agregarNovela**: Añade una novela a la lista.
- **updateNovelas**: Actualiza la lista de novelas.

### NovelaRepository
- **buscarNovelaPorTitulo**: Busca una novela por título en Firestore.
- **agregarNovela**: Añade una novela a Firestore.

### NovelaDetailFragment
- **showNovelaDetails**: Muestra los detalles de una novela seleccionada.

### NovelaListFragment
- **onCreateView**: Inicializa el fragmento y configura el RecyclerView.
- **onNovelaSelected**: Maneja la selección de una novela.
- **onFavoritoClick**: Alterna el estado de favorito de una novela.
- **onDeleteClick**: Elimina una novela de la lista.

### NovelaWidgetProvider
- **onUpdate**: Actualiza el widget de la aplicación con los datos más recientes.

### SyncTask
- **doInBackground**: Realiza la sincronización de datos en segundo plano.
- **onPostExecute**: Maneja el resultado de la sincronización.

## Perfilado de Memoria

He utilizado el Android Profiler para monitorear el uso de memoria de la aplicación. El profiler ayuda a identificar fugas de memoria y optimizar el rendimiento de la aplicación proporcionando datos en tiempo real sobre la asignación de memoria y la recolección de basura.

URL:https://github.com/inesgmz/novelas4.git

URLDATABASE: https://novelas4-f9837-default-rtdb.firebaseio.com/
