# NYTimesMostPopular

Használt elemek :
*     - Retrofit : NYTimes API elérése.
*     - GsonConverter : Response JSON feldolgozása.
*     - Picasso : ImageView URL alapján való kitöltése.
*     - CircleImageView : Kör alakú ImageView a design miatt.
*     - Groupie : GroupAdapter használata a gyors fejlesztés érdekében.
*     - Room : Room használata SQLite adatbázishoz.
*     - RecyclerView : Lista elemek tárolásához.
*     - Design : Snackbar használata.


Az alkalmazás megjeleníti a NYTimes API-n keresztül a Most Popular cikkeket egy listában. A lista elemeire tudunk nyomni, amik egy új ablakra navigálnak a cikk egy nagyobb méretű megjelenítéséhez. Az új ablakon alul található gombra nyomva a Crome-ban megjelenik az adott cikk teljes egésze. Az alkalmazás figyeli az internet elérhetőségét és ha nincs csatlakoztatva az internethez, akkor ezt Snackbar-on jelzi, illetve nem is engedi megnyitni a linkeket a Crome-ban a gombra kattintva.


