# Novelas6

He utilizado el repositorio de novelas4 para el de novelas6

## Clases y Métodos Importantes

### MainActivity
- **onCreate**: Inicializa la actividad principal, configura el RecyclerView y maneja los clics de los botones para navegar a otras actividades.
- **eliminarNovela**: Elimina una novela de la lista.
- **mostrarNotificacionFavorito**: Muestra una notificación cuando una novela se añade o se elimina de favoritos.
- **showNovelaDetails**: Abre los detalles de una novela seleccionada.

### MapsActivity
- **onCreate**: Inicializa el MapView y añade marcadores para la ubicación de cada novela.
- **onResume**: Carga la configuración del mapa.
- **onPause**: Guarda la configuración del mapa.

### AgregarResenaActivity
- **onCreate**: Configura la interfaz de usuario para añadir una reseña a una novela.
- **btnGuardarResena.setOnClickListener**: Guarda la reseña y actualiza la lista de reseñas de la novela.

### AjustesActivity
- **onCreate**: Configura la interfaz de usuario para ajustar la configuración de la aplicación, incluyendo los interruptores de modo claro y oscuro.
- **updateNovelas**: Actualiza la lista de novelas en el RecyclerView.

### DetallesNovelaActivity
- **onCreate**: Muestra los detalles de una novela seleccionada.

### FavoritosActivity
- **onCreate**: Configura el RecyclerView para mostrar las novelas favoritas.
- **eliminarNovela**: Elimina una novela de la lista de favoritos.
- **mostrarNotificacionFavorito**: Maneja las notificaciones de favoritos.

### LoginActivity
- **onCreate**: Configura la interfaz de usuario para el inicio de sesión del usuario.
- **buttonLogin.setOnClickListener**: Autentica al usuario con Firebase.

### RegisterActivity
- **onCreate**: Configura la interfaz de usuario para el registro de usuarios.
- **buttonRegister.setOnClickListener**: Registra un nuevo usuario con Firebase.

### FirebaseService
- **obtenerNovelas**: Recupera la lista de novelas desde Firebase.
- **buscarNovelaPorTitulo**: Busca una novela por título en Firebase.
- **compressData**: Comprime datos usando GZIP.
- **createRetrofitInstance**: Crea una instancia de Retrofit con caché.

### Novela
- **Implementación de Parcelable**: Permite que la clase `Novela` se pase entre actividades.
- **CREATOR**: Genera instancias de la clase `Novela` desde un `Parcel`.

### NovelaAdaptador
- **onCreateViewHolder**: Crea view holders para el RecyclerView.
- **onBindViewHolder**: Vincula datos a los view holders.
- **getItemCount**: Devuelve el número de elementos en la lista.
- **agregarNovela**: Añade una novela a la lista.
- **updateNovelas**: Actualiza la lista de novelas.

### NovelaRepository
- **buscarNovelaPorTitulo**: Busca una novela por título en Firestore.
- **agregarNovela**: Añade una novela a Firestore.

### DataSyncJobService
- **onStartJob**: Inicia la tarea de sincronización de datos.
- **onStopJob**: Maneja los eventos de detención del trabajo.

### DataSyncTask
- **doInBackground**: Realiza la sincronización de datos en segundo plano.
- **onPostExecute**: Notifica al usuario cuando la sincronización se completa.

### DataSyncReceiver
- **onReceive**: Inicia el servicio de sincronización de datos cuando se detecta un cambio de red.

### NetworkChangeReceiver
- **onReceive**: Inicia el servicio de sincronización de datos cuando se detecta un cambio de red.

### NovelaDetailFragment
- **onCreateView**: Infla el diseño del fragmento.
- **showNovelaDetails**: Muestra los detalles de una novela seleccionada.

### NovelaListFragment
- **onCreateView**: Infla el diseño del fragmento y configura el RecyclerView.
- **onNovelaSelected**: Maneja los eventos de selección de novelas.
- **onFavoritoClick**: Maneja los eventos de clic en favoritos.
- **onDeleteClick**: Maneja los eventos de clic en eliminar.

### NovelaWidgetProvider
- **onUpdate**: Actualiza el widget de la aplicación con los datos más recientes.

### SyncTask
- **doInBackground**: Sincroniza datos con Firestore en segundo plano.
- **onPostExecute**: Notifica al usuario cuando la sincronización se completa.


URL:https://github.com/inesgmz/novelas4.git

URLDATABASE: https://novelas4-f9837-default-rtdb.firebaseio.com/
