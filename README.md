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

**/!\ Il est important de noter que les petits ralentissements lors d'une partie contre l'IA ne sont pas liés à des problèmes d'optimisation mais bien volontaires. En effet, notre IA étant assez puissante, nous avons décidé de marquer de courtes pauses entre chacun de ses mouvements afin de permettre au joueur de bien comprendre comment elle a joué (et pour éviter les accusations de tricherie).**

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

## Les différents packages et classes

- **com.nidras.lettergame** :
   Ce package contient les principales classes composant le coeur du programme. Celles-ci ont pour principal rôle de poser les    fondations du jeu.

  - App : Classe principale ayant pour unique but de créer l'instance du jeu (classe Game).
  - Dictionary : Cette classe gère tout ce qui est en relation avec le dictionnaire et le traitement de mots et chaînes de caractères diverses et variées. Cette classe commence par importer le dictionnaire, traiter chaque mot afin d'enlever tous les charactères différents des 26 lettres de l'alphabet de base (sans accents, tirets, espaces, ...) et retirer les occurences d'un même mot générées à cause du traitement. Par la suite, cette classe dispose d'un grand nombre de méthodes ayant pour but de chercher tous les anagrammes d'un mot, vérifier que deux mots ont le même nombre de lettres, vérifier qu'un mot appartienne au dictionnaire, compter le nombre de voyelles (pour l'IA), ...
  - GUI (Graphic User Interface) : Classe commune à de nombreux programmes, ayant pour objectif de proposer une interface graphique à l'utilisateur et de relier chaque composant graphique (bouton, champ de texte, ...) à une fonctionnalité ou une méthode d'une autre classe.
  - Game : Cette classe est la classe principale du jeu, elle crée les différents objets, décide quel joueur pourra commencer le premier, gère le tour des différents joueurs, permet de recommencer la partie, vérifie que la partie n'est pas terminée, etc ... Par ailleurs, les fonctions des tours des joueurs mettent "le code en pause" jusqu'à que le joueur passe son tour via la classe GUI (qui réveille l'objet Game).
- **com.nidras.lettergame.players**
   Ce second package est pourvu de classes gérant les différentes actions des joueurs et leurs caractéristiques.
  - IA : La première classe de ce package gère l'intelligence artificielle dans son intégralité. Elle contient des méthodes ayant uniquement pour but de proposer différentes actions de jeu possibles à l'IA. L'IA va commencer par créer le plus de mots possibles à partir des différentes lettres de pool commun suivant un paramètre l'autorisant à jouer des mots de plus en plus courts en favorisant ceux possédant le plus de voyelles possible afin d'handicaper le joueur. Par la suite, l'intelligence artificielle va voler le plus de mots possible aux joueur (équivalent commande /s) dans le but de gagner le plus de mots possible. Une extension aurait été de poursuivre en volant des mots via des anagrammes et en vérifiant s'il ne peut pas recréer de nouveaux mots (commande /c) car ses actions ont générées des lettres dans le pool commun. Nous avons considéré ce choix comme "anti-fun" car le joueur n'aurait aucune chance de gagner contre l'IA.
  - Player : Cette classe a pour objectif de représenter l'implémentation d'un joueur.
  - Plays : Plays contient toutes les méthodes permettant d'appliquer les commandes de jeu dans les deux modes de jeu différents.
- **com.nidras.lettergame.pool**
Ce package va contenir une interface et ses implémentations permettant de gérer le fonctionnement des différents pools de mots et lettres.
  - LetterPool : Cette interface va servir de modèle aux différentes classes. Elle contient des méthodes permettant d'interagir avec les listes de mots et lettres tout en les sécurisant (principe d'encapsulation). Par ailleurs, cette interface imposera aussi des méthodes dont le rôle sera de concatener le contenu du pool, vérifier qu'un mot contient un mot d'un pool ou bien l'inverse.
  - CommonPool : Première implémentation de LetterPool, CommonPool régit un pool composé de lettres accessibles aux deux joueurs. Les méthodes détaillées ci-dessus sont adaptées au type Character et permettent d'interagir avec le pool tout en sécurité.
  - IAPool : IAPool n'est autre que le pool de l'intelligence artificielle, contenant des mots (String) et proposant diverses méthodes permettant de vérifier l'existence d'un mot au sein de la zone de jeu de l'IA ou encore de voir s'il existe un mot dans le pool qui pourrait être contenu par un mot mis en paramètre (utile pour les commandes /l par exemple).
  - PlayerPool : Le fonctionnement de la classe PlayerPool est identique à celui de l'IAPool sauf quelques différences minimes engendrées par le fonctionnement général du code (fonctionnalités plus précises).
