#SIMBUG Open Standard

##What a SIMBUG is
A SIMBUG is an acronym for a *Computerized turn-based Simulation Business Game*.

A SIMBUG is considered to have the following workflow:

![simbug_pattern](https://cloud.githubusercontent.com/assets/13544631/8970933/339c6ed6-3655-11e5-92d7-e32dc0f66b1e.png)


##Entities
###Game
One realization of a definition file

###Player(s)
A human or non-human subject that interacts with the game. It must be identified by a [uuid](https://en.wikipedia.org/wiki/Universally_unique_identifier)


###Variables
Place-holders for things that their values might change during the simulation. 
Variables can be any type of:
* Integer
* String
* Float
There are two type of variables: Player Choices and External Variables.

###Parameters
Place-holders for things that their value will not change during a game.
Parameters can be any type of:
* Integer
* String
* Float
A Parameter can either have an initial value or an initial assignment function.

###Player Choices Variables
Variables that within each turn every player gives values to. 


###External Variables
Variables that their value is calculated from an outside procedure. 
For example this could refer to a call to a REST API from another server in the web.
For each external variable an assignment function should be declared.


###Random Numbers


###Configuration Parameters


###Algorithm


###User State Variables


###World State Variables


##Definition File

