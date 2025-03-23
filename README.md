# Activitat 1

## Objectius

Practicar els conceptes bàsics de programació en Scala_
  - Funcions i mètodes
  - Estructures de dades simples
  - Tests bàsics usant la llibreria `munit`

## Importació del projecte

El projecte està configurat per a ser usat amb `sbt`. Per importar-lo a l'`IntelliJ` la forma més simple és fer `Open...` i seleccionar `build.sbt` i dir que es vol carregar com a projecte.

## Estructura de l'activitat

L'activitat consta de diferents fitxers que tenen exercicis similars als que hem fet, centrant-se en el tema de les estructures de dades (els temes anteriors són de conceptes inicials o presentació del llenguatge de programació).

1. Tipus llista amb la definició que hem fet amb un enum segons el que hem vist als apunts
2. Tipus llista de la llibreria estàndard
3. Arbres, en aquest cas, arbres binaris de cerca

Per a cada apartat, hi ha un fitxer que conté el codi bàsic, que s'ha de completar, i un esquelet de joc de proves, que prova alguna de les funcions que us demanem (joc que proves que, òbviament, necessitareu ampliar).

NOTA: Per evitar problemes d'indentació, he usat la notació de scala que usa claus. Vosaltres podeu fer servir l'estil que voleu.

### Preludi: ListAndOrderings

Com a l'activitat alguna de les operacions necessita tenir un criteri d'ordenació sobre els elements de la llista, utilitzarem el tipus `Ordering[A]` que és l'_equivalent_ al `Comparator<A>` en Scala. Estudieu bé el codi, i com manegar-se amb `Ordering[A]` de la forma més convenient. 

Aquest codi l'explicarem a classe i, a més, està profusament comentat.

### List

#### partition

Donada una llista i un predicat, retorna un parell de llistes, la primera de les quals conté els elements que compleixen el predicat, i la segona, els que no el compleixen.

Podeu fer vàries versions (que podeu denominar: partition, partition2, partition3, etc):

* recursivitat directa
* recursivitat final
* foldRight
* foldLeft

L'objectiu és "jugar" amb totes les possibilitats que hi ha per entendre millor com lliguen les recursivitats, els folds, els ordres dels elements a les llistes, etc., etc.

NOTA: Penseu, per exemple, en les funcions que ja teniu definides com `reverse`. 

#### digitsToNum

Donada una llista amb els dígits d'un número retornar el valor numèric (com enter, d'aquest número).

En aquesta primera versió suposarem que la llista només conté dígits, és a dir, que tots els valors que conté són enters al rang [0, 9].

Feu dues versions:

* recursiva (ja decidireu si directa o amb alguna funció auxiliar perquè sigui recursiva final)
* fold (ja decidireu de quina mena)

NOTA: Passar a String i després fer un toInt no és la solució que busquem, encara que pot servir d'inspiració.

#### insertion sort

Ordenar la llista per inserció. La funció que passem com a paràmetre indica si dos elements estan ordenats.

PISTA: Haureu de definir una funció auxiliar per inserir de forma ordenada un element en una llista (que ja està ordenada).

#### merge sorted

Fusiona dues llistes ordenades en una de sola (que també ho estarà). També passem com a paràmetre el criteri d'ordenació.

Per aquesta funció no hi ha cap funció de test, per tant, cal que l'escriviu vosaltres.

NOTA: Suposarem que és cert que les dues llistes estan ordenades per aquest criteri d'ordenació.

#### check sorted

Donada una llista i una funció que indica el criteri d'ordenació, comprovar que realment la llista està ordenat per aquest criteri.

#### find

Donada una llista i un predicat, retornes el primer element de la llista que compleix el predicat. Com podria ser que no n'hi hagués cap, utilitzem el tipus Some.

L'única cosa que hem de conèixer del tipus Option (predefinit a la biblioteca estàndard de Scala), és que la seva definició és equivalent a:

  ```
  enum Option[+A]:
    case None
    case Some(a: A)
  ```

#### partition map

Semblant al `partition`, però en comptes de tenir un booleà que m'indica a quin lloc van a parar els elements originals, tinc una funció que els transforma en dues variants (representades pel tipus Either)

L'única cosa que hem de conèixer del tipus Either (predefinit a la biblioteca estàndard de Scala), és que la seva definició és equivalent a:

  ```
  enum Either[+E, +A]:
    case Left(e: E)
    case Right(a: A)
  ```

### StdList

Aquests exercicis també van sobre llistes, però usen les predefinides en scala.

Per la documentació sobre les llistes podeu consultar la [documentació de la llibreria estàndard de scala](https://www.scala-lang.org/api/3.3.3/)

Al principi del fitxer StdLib, teniu una llista de mètodes amb els quals és important que us familiaritzeu abans de fer els exercicis. 

Fixeu-vos, que hi ha mètodes que només existeixen per alguns "valors" dels tipus genèrics. Per exemple, el mètode `sum`, està definit a les llistes d'enters (i retorna un enter), però no podem fer un `sum` d'una llista de strings (i falla en compilació).

Com sempre, **proveu de fer diverses versions**: l'objectiu és ser flexible a l'hora de pensar solucions i no centrar-se en una única manera de fer les coses.

Abans de fer-les, implementeu jocs de prova per a provar-les.

#### count lengths

Ha de retornar un mapa (`Map`) en el que, per a cada longitud, hi hagi el nombre de paraules amb aquesta longitud.

#### sum first powers of b

Fa la suma b^0 + b^1 + b^2 + ... + b^(n-1)

No feu servir `math.pow` doncs retorna un double i això provoca problemes d'arrodoniment.

### BST

Aquest exercici presenta un ADT que representa els arbres binaris de cerca.

Per simplificar algunes coses, i no haver d'introduir més elements de Scala com les typeclasses i els paràmetres implícits, farem que els mètodes que ho requereixin, rebin la funció que indica l'ordre sobre els elements de l'arbre com a paràmetre.

#### insert

Fixeu-vos que és un mètode (l'arbre sobre el qual s'aplica és this que és de tipus BST[A]) i rep l'element que es vol inserir (que és de tipus B, amb R restringit als supertipus de A) i es rep la funció que indica si dos elements de tipus B estan ben ordenats, per a moure's dins de l'arbre.  El resultat és un nou BST[B] que, òbviament, compartirà parts dels nodes amb l'arbre de partida. Si l'element ja es trobava a l'arbre, aquest queda igual.

Tal com s'explica al document que us adjunto (Laboratori 4 d'estructures de dades del curs 2022/2023) en inserir es va fent una còpia del camí des del node arrel fins al punt d'inserció. No cal que optimitzeu el cas en què, en un punt del camí detecteu que l'element hi és i realment no calia copiar res. Això es comenta a l'apartat de tasques complementàries de la pràctica.

#### contains

Indica si l'element buscat es troba a l'arbre.

#### fold

Versió del fold per aquest ADT.

#### fromList

Retorna un arbre construït per la inserció de tots els elements de la llista.

Com a llistes, feu servir les de la biblioteca stàndard de scala, amb les que us heu familiaritzat a l'apartat anterior.

#### inorder

Recorregut en in-ordre de l'arbre.

Com a llistes, feu servir les de la biblioteca stàndard de scala, amb les que us heu familiaritzat a l'apartat anterior.

### D'Hondt

Finalment, us presento un problema complex que resoldrem funcionalment. Una mica com a les pràctiques de primer, us presentaré una possible descomposició del problema en subproblema, i cadascun d'aquests apartats serà la tasca a resoldre.

Com el nom de l'apartat indica, el problema a resoldre és el de repartir els escons en una circumscripció, donats els vots a cada partit. Per simplificar, no considerarem ni els vots en blanc ni els nuls, i ja harem filtrat les candidatures que no arriben al mínim percentatge de vots.

La descripció de cada mètode la trobareu al fitxer de partida i, al test, els resultats que va haver-hi a la circumscripció de Lleida i el repartiment resultant.

A l'enllaç [Procediment Electoral](https://www.parlament.cat/pcat/parlament/que-es-el-parlament/procediment-electoral/), concretament a la secció **El resultat electoral** en trobareu informació

A l'apartat anterior teniu una llista de mètodes que poden ser interessants.

NOTA: Altres descomposicions són possibles i podeu fer dissenys alternatius. Com més coses proveu, més aprendreu.

## Consells de realització

L'ordre suggerit de resolució és el següent és el suggerit al readme (excepte per les parts que requereixen el tema 5).

No cal que us preocupeu inicialment d'aspectes d'eficiència o de si la solució és *stack-safe* o no, però es valorarà que resoleu un mateix problema de diferents maneres amb diferents característiques.

En alguns casos indico que la solució que busquem és usant per exemple un `foldRight`. Si no trobeu aquesta solució, podeu fer-ne una diferent.

Com hem fet a classe, el procediment a seguir pot ser:

* solució evident i intuïtiva (possiblement via pattern-matching o recursivitat explícita)
* solució usant altres combinadors
* anàlisi de les solucions anteriors en termes d'eficiència, stack-safeness, etc.
* proposta d'altres solucions

Per cada fitxer he creat una petita classe de proves perquè podeu afegir tests que comprovin el funcionament del vostre codi.

També podeu usar worksheets per a fer-ho interactivament (teniu un exemple de worksheet al directori `worksheets`)

Si voleu saber una mica més les possibilitats de la biblioteca de tests, podeu consultar la seva documentació ([munit homepage](https://scalameta.org/munit/)).

## Què heu d'entregar

* Projecte amb la resolució dels exercicis, amb els tests o workflows que heu afegit.

* Per poder tenir diverses solucions del mateix problema podeu donar-los noms numerats (p. ex. `partition`, `partition2`, `partition3`, etc.).
* Per poder tenir diverses solucions del mateix problema podeu donar-los noms numerats (p. ex. `partition`, `partition2`, `partition3`, etc.).

* Informe, en PDF, explicant el procés de resolució de cada problema.
