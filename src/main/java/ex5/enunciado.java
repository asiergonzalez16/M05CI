package ex5;

public class enunciado {
    /*
    Heu de fer la mateixa pràctica de proves unitàries i fer-la ben feta (mireu-vos els comentaris que us vaig deixar
    o pregunteu-me i us ajudaré).
    Heu de fer la següent ampliació a la pràctica un cop estigui acabada.
    Heu de crear un nou package "ex5".

    Heu de modificar la taula de hash perquè no hi hagi col·lisions. Quan es produeix una col·lisió
    (un ítem amb un hash diferent va a parar a una posició ja ocupada), ja no fem servir una llista enllaçada,
    sinó que fem el següent:

    Cal crear un nou array d'ítems amb el doble del tamany original.

    Cal recórrer l'antic array, i per cada posició trobada, trobar quina és la seva posició dins el nou array per a
    "moure" l'element.

    Si la nova posició està ocupada (col·lisió), cal tornar al punt 1 (recursivitat).

    Haureu de modificar molt de codi dins la taula de hash, el mètode "add" es complica una mica, però la resta de mètodes,
    com que no hi ha col·lisions i, per tant, no hi ha la necessitat de fer servir cap "next" se simplifica moltíssim.

    Haureu de modificar les proves unitàries, perquè ara ja no existeixen col·lisions, però sí que es modificarà el "size"
     que ja no valdrà sempre 16. Caldrà que comproveu que tots els mètodes funcionen com s'espera (tot funciona igual,
     però sense col·lisions), sobretot comprovant que el "size" es modifica quan toca.
*/
}
