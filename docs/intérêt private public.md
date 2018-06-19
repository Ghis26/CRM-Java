Intérêt de l'accessibilité en Java

En mettant l'ensemble des attributs d'une classe en private, cela permet de ne pas pouvoir les modifier de l'extérieur.

Pour les afficher, il est nécessaire que chaque attribut ait un Getter "accesseur".

Pour les modifier, il est nécessaire que chaque attribut ait un Setter "modificateur". Nous pouvons, dans ce setter, ajouter une fonction de vérification (par exemple pour un age, ne pas mettre de nombre négatif).

De ce fait, le private permet de sécuriser les données. C'est pour cela qu'il faut mettre TOUS les attributs en private, y accéder uniquement avec get et set, ainsi que la plupart des méthodes.

Le public est utilisé pour avoir accès à certaines méthodes en dehors de la classe (par exemple des méthodes nécessaires au bon fonctionnement du programme).
