# Novelas4
## Classes and Important Methods

### `MainActivity`
- **onCreate**: Initializes the main activity, sets up the RecyclerView, and handles button clicks for navigation.
- **fetchNovelasFromFirebase**: Fetches novels from Firebase and updates the RecyclerView.
- **eliminarNovela**: Deletes a novel from the list.
- **mostrarNotificacionFavorito**: Shows a notification when a novel is marked as favorite.

### `AjustesActivity`
- **onCreate**: Sets up the activity, initializes switches for light and dark modes, and handles theme changes.
- **updateNovelas**: Updates the list of novels in the RecyclerView.

### `AgregarResenaActivity`
- **onCreate**: Initializes the activity, sets up the spinner with the list of novels, and handles saving reviews.

### `FavoritosActivity`
- **onCreate**: Initializes the activity, sets up the RecyclerView with favorite novels, and handles button clicks for navigation.
- **eliminarNovela**: Deletes a novel from the list of favorites.
- **mostrarNotificacionFavorito**: Shows a notification when a novel is marked as favorite.

### `DetallesNovelaActivity`
- **onCreate**: Initializes the activity and displays the details of the selected novel.

### `NovelaAdaptador`
- **onCreateViewHolder**: Creates view holders for the RecyclerView.
- **onBindViewHolder**: Binds data to the view holders.
- **getItemCount**: Returns the number of items in the list.
- **agregarNovela**: Adds a novel to the list.
- **updateNovelas**: Updates the list of novels.

### `Novela`
- **Parcelable implementation**: Allows the `Novela` class to be passed between activities.

### `FirebaseService`
- **obtenerNovelas**: Fetches novels from Firebase and returns them via a callback.

### `NovelaRepository`
- **buscarNovelaPorTitulo**: Searches for a novel by title in Firebase.
- **agregarNovela**: Adds a novel to Firebase.

### `SyncTask`
- **doInBackground**: Fetches novels from Firebase in the background.
- **onPostExecute**: Displays a toast message upon completion.

### `NovelaWidgetProvider`
- **onUpdate**: Updates the app widget with the latest data.

### `NetworkChangeReceiver`
- **onReceive**: Detects network changes and starts the data sync service.

### `DataSyncReceiver`
- **onReceive**: Starts the data sync service upon receiving a broadcast.

### `DataSyncJobService`
- **onStartJob**: Starts the data sync task.
- **onStopJob**: Handles job stop events.
- **DataSyncTask**: Performs the data sync in the background.

URL:https://github.com/inesgmz/novelas4.git

URLDATABASE: https://novelas4-f9837-default-rtdb.firebaseio.com/
