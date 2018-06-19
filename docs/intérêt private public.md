Int�r�t de l'accessibilit� en Java

En mettant l'ensemble des attributs d'une classe en private, cela permet de ne pas pouvoir les modifier de l'ext�rieur.

Pour les afficher, il est n�cessaire que chaque attribut ait un Getter "accesseur".

Pour les modifier, il est n�cessaire que chaque attribut ait un Setter "modificateur". Nous pouvons, dans ce setter, ajouter une fonction de v�rification (par exemple pour un age, ne pas mettre de nombre n�gatif).

De ce fait, le private permet de s�curiser les donn�es. C'est pour cela qu'il faut mettre TOUS les attributs en private, y acc�der uniquement avec get et set, ainsi que la plupart des m�thodes.

Le public est utilis� pour avoir acc�s � certaines m�thodes en dehors de la classe (par exemple des m�thodes n�cessaires au bon fonctionnement du programme).
