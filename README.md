
# Fetchify

Kotlin app using MVVM model with Clean architecture patterns following Uncle Bob’s book. For the purpose of this application, which was to fetch a list from an API and display it in a RecyclerView, I would first like to point out that this pattern for this use case is _definitely overkill_; however, I wanted to put my best foot forward and showcase some production-esque code. 

The app:
-_ Is offline-first:_ I used a Room database to cache every time the app is initialized. So the lists fetched from the endpoint are first stored in the Room database. The sorted items for the RecyclerView are fetched from this database by the use case layer.
- _Uses Dagger Hilt for dependency injection._
- _Uses Data and View Binding._
- _Uses Kotlin Flows, coroutines, and LiveData._
- _Displays in RecyclerView:_ The list items retrieved are displayed on a RecyclerView.

To run the app, simply build and run!
