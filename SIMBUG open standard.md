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

Several types of variables exist, each with different semantics: 

1. Player Choices Variables
2. External Variables
3. User State Variables 
4. World State Variables

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


###Random Number Generators
Many Random Number Generators (RNG) can be defined. 
Each of them must return a float value in [0,1] and a calculation function should be declared.


###Configuration Parameters
Parameters that are defined for a game.

###Choices-To-State Algorithm
This is the algorithm defining the feedback loop between the player choices and their state and the state of the world

###User State Variables 
Variables that are attached to a player but are not directly affected by him. 
They represent the state of any player.
Their semantics are the results of (all) players actions to a specific player. 

###World State Variables
Variables that are attached to the whole game.
Their semantics are the results of (all) players actions to a specific aspect of the game environment. 

##Definition File
A SIMBUG game can be fully represented by a definition file (DeF). 
In the definition file several special placeholders can be used:
* {JAVA} ... {/JAVA}: java code is included
