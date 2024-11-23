Application Android pour l'école d'ingénieurs ISIS
Description

Ce projet est une application Android développée dans le cadre d’un travail pour l’école d’ingénieurs ISIS. L’objectif principal est de mettre en pratique des notions liées au développement Android, notamment l’intégration d’une API externe et l’utilisation des outils modernes comme Jetpack Compose et les layouts adaptatifs.

L’application utilise l’API TMDB pour récupérer diverses informations sur les films, les séries et les acteurs. Elle propose une interface interactive permettant d’explorer les fonctionnalités suivantes :

    Utilisation d’une API externe pour la récupération de données.
    Implémentation de layouts adaptatifs avec Jetpack Compose pour différents formats d’écran.
    Compréhension de la gestion des états et des composants modernes de l’architecture Android.

Structure du dépôt

Ce dépôt contient plusieurs branches, chacune dédiée à des fonctionnalités ou objectifs spécifiques :

    Main
        Version propre, fonctionnelle et finalisée de l’application.
        Illustre l’utilisation correcte de Jetpack Compose et l’intégration de l’API TMDB.

    Revisions
        Contient des exercices conçus pour réviser les partiels.
        Comprend des tâches pratiques et des informations pour revoir le cours.

    HILT+Room
        Une branche expérimentale visant à intégrer HILT (injection de dépendances) et Room (base de données locale).
        L’objectif est de créer une application fonctionnant aussi bien en ligne qu’hors ligne.
        Remarque : Cette branche est encore en développement et n’est pas entièrement opérationnelle.

Fonctionnalités

    Section Films : Affiche les films tendance et populaires, avec des informations détaillées pour chaque film.
    Section Séries : Liste les séries TV en tendance, avec leurs détails et les acteurs associés.
    Section Acteurs : Présente les acteurs tendance ainsi que leurs œuvres connues.
    Layouts Adaptatifs : Utilisation de WindowSizeClass pour ajuster l’interface en fonction des tailles d’écran.
    Gestion des états : Exploite les composants stateful de Jetpack Compose pour une interface réactive et dynamique.

Outils et Technologies

    Android Studio
    Kotlin
    Jetpack Compose
    API TMDB
    HILT (Injection de dépendances - Expérimental)
    Room (Base de données locale - Expérimental)

Améliorations Futures

    Finaliser la fonctionnalité hors ligne dans la branche HILT+Room.
    Améliorer la gestion des erreurs et l’expérience utilisateur dans l’application principale.

Instructions d’utilisation

    Cloner le dépôt : https://github.com/Estadieu/TMDb_App.git

    Basculer vers la branche souhaitée : git checkout <nom-de-la-branche>
