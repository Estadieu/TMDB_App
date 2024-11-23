package com.example.my_application1.ui.theme

/* Exemple composant graphique vide avec jetpack compose + anotation + 5Modifier (Q1,Q2)
@Composable
 fun EmptyBox(
    modifier: Modifier = Modifier
) {
    // Box est un conteneur de base pour afficher une seule couche.
    Box(
        modifier = modifier
            .size(100.dp) // Définit une taille
            .background(Color.Gray) // Modifie arrière plan
            .clickable //Action qui est executer au click
            .Border //Permet de styliser le composant
            .Padding // Espace entre les bord d'un composant
    )}
 */

/* Le composant scaffold -> permet de gérer le contenu des emplacement clasique d'une application android -> Toolbar, barre de nav, Drawer ...
 -> Variable interne au composant :  var textevisible by remember { mutableStateOf(true) }
 -> Bibli Coil -> Permet d'ajouter une img d'une URL extérieur
 -> Adaptation de la taille :
  val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
          Screen(windowSizeClass)
val classeHauteur = classes.windowHeightSizeClass
  val classeLargeur = classes.windowWidthSizeClass
  when (classeLargeur) {
    WindowWidthSizeClass.COMPACT -> /* largeur faible */
    WindowWidthSizeClass.MEDIUM -> /* largeur moyenne */
    WindowWidthSizeClass.EXPANDED -> /* grande largeur *

val navBackStackEntry by navController.currentBackStackEntryAsState()
val currentDestination = navBackStackEntry?.destination

// est-ce qu'on est sur Destination3 ?
val isDestination3 = currentDestination?.hasRoute<Destination3>() == true  -> Connaitre desti

RETROFIT -> Biblio pour accéder à une API HTTP sous forme d'une interface KOLTIN : Définition types KOLTIN (correspondent aux résultats JSON de l'API)
Appel des méthodes de l'API dans le viewModel, comme methode supend -> viewModelScope, MutableStateFlow -> permet de stocker le résultats

ViewModel -> Classe pour stocker et stocker données relative a l'interface graphique, données réssistes aux changement de configuration de l'appareil, données sont obesrvable, traite les actions de l'utilisateurs.
 LazyColumn, LazyRow, LazyVerticalGrid et LazyHorizontalGrid -> Affiche des listes de composants que quand ils sont visible

 @GET("search/{id}") suspend fun methode(@Path("id") id: String, @Query ("langue") langue: String): String
 Quel est l'URL construit par ce code : val api = Retrofit.builder() .baseUrl("https://api.org/ . buil() api.methode("a32", "fr")
 réponse -> https://api.org/search/a32?langue=fr
 */
 */









