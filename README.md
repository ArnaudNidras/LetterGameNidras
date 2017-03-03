## LetterGameNidrasDraven
Groupe : Arnaud PIRIOU et Franck VALOT

Ce programme est une implémentation du Letter Game, répondant aux différentes consignes et contraintes posées sur la page suivante : 
https://github.com/MLabusquiere/TP_4A_2017_Letter_Game

## Installation et exécution avec Maven (sous Linux)

- Télécharger le projet
- Se déplacer dans **"LetterGameNidrasDraven-master/lettergametp/"**
- Compiler et tester avec **"mvn clean install"**
- Exécuter avec **"java -jar './target/lettergametp-0.0.1-SNAPSHOT.jar'"**

## Comment jouer ?

Tout d'abord, le programme va se lancer dans le mode de jeu joueur contre IA. Il est possible de changer ce mode de jeu en joueur contre
joueur en cliquant sur le bouton en haut de la fenêtre.

L'interface graphique contient différents éléments. En haut de la fenêtre se trouvent le bouton permettant de changer le mode de jeu ainsi
qu'un label servant à indiquer qui doit jouer, les erreurs, etc ...
Par la suite, nous retrouvons 3 zones de texte au milieu de la fenêtre. La première correspond au PlayerPool (zone de jeu du joueur/joueur 1),
contenant les différents mots que ce joueur a obtenu. La deuxième quant à elle, n'est autre que le CommonPool(zone de jeu contenant les
différentes lettres piochées par les joueurs). Enfin, la dernière correspond à la zone de jeu de l'IA ou du joueur 2, contenant les différents
mots joués par ce joueur).

En bas de la fenêtre se trouvent deux boutons et un champ de texte. Le premier bouton sert au joueur à passer son tour lorsqu'il ne peut plus
jouer de nouveau mot. Le champ de texte est l'équivalent d'une console dans laquelle les joueurs peuvent entrer différentes commandes afin
de jouer (voir liste plus bas). Le dernier bouton ("ok"), sert à envoyer la commande saisie.

Les joueurs doivent contrôler 10 mots pour gagner. Bien sûr, il y a différentes manières de gagner (enfin si notre IA vous le permet ;) ). 
Dans le paragraphe suivant, nous détaillerons les différentes manières de gagner des mots et de se protéger.

**/!\** Il est important de noter que les petits ralentissements lors d'une partie contre l'IA ne sont pas liés à des problèmes d'optimisation
mais bien volontaires. En effet, notre IA étant assez puissante, nous avons décidé de marquer de courtes pauses entre chacun de ses mouvements
afin de permettre au joueur de bien comprendre comment elle a joué (et pour éviter les accusations de tricherie).

## Les différentes commandes

- **/c motACreer** :
Permet de créer un mot grâce aux lettres du pool commun.
Exemple : /c voiture -> Ajoute "voiture" à la liste de mots du joueur.

- **/s motAVoler motResultant** :
Permet de voler un mot à l'adversaire en ajoutant une ou plusieurs lettres provenant du pool commun.
Exemple : /s voiture voitures -> Vole le mot "voiture" à l'adversaire en prélevant un 's' dans le pool commun puis ajoute la concaténation dans la liste de mots du joueur.

- **/a motA motB motAB** :
Concatène deux mot du pool du joueur dans le but d'en créer un nouveau.
Exemple : /a garde manger gardemanger -> Combine "garde" et "manger" pour créer le mot "gardemanger".

- **/l motARalonger motResultant** :
Ajoute des lettres du pool commun à un mot du pool du joueur pour changer celui-ci en un nouveau nom plus long.
Exemple : /l parle parlement -> Ajoute les lettres 'ment' en provenance du pool commun pour changer le mot "parle" en "parlement".

- **/ana ADV/MOI motDOrigine anagramme** :
Cette commande a pour but de voler ou modifier un mot afin d'en faire un anagramme. Il faudra choisir entre "MOI" qui précise que le
joueur change un de ses mots en son anagramme ou "ADV" qui permet de voler un mot adverse en utilisant son anagramme. Cependant chaque anagramme ne peut être créé qu'une seule et unique fois.
Exemple : /ana ADV olive voile -> Vole le mot olive au joueur adverse et ajoute le mot voile au joueur.