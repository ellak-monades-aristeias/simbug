#SIMBUG Open Standard

##Open Standard Components
A SIMBUG is composed of **structural static elements** and of a **dynamic aspect** concering the communication of the game's business logic with the presentation layer.

The structural elements are the entities of the business logic definition while the communication aspect will deal with the API annotations of the RESTful server.

It is necessary to clarify the following definitions from the beggining. 

* **Game** will refer to the business logic and will be fully defined in a definition file.
* **Game realization** will pertain to a play-cycle of a game. 
* A **Player** will be a human or non-human subject that interacts with the game realization through the presentation layer.


##Business Logic Definition Entities

A Simulation Business Game (SIMBUG) is considered to be sufficiently modelled by the workflow of th following figure:

![simbug_pattern](https://cloud.githubusercontent.com/assets/13544631/8996812/bfa27e74-3723-11e5-812e-8e344d365688.png)

Several players are making several choices. Those choices are treated as input, among other things (external ddata, random numbers and configuration data) in order to update some (user or/and world) state variables through some kind of algorithm.

Thus the entities of the figure above are what is needed to be defined by a game designer in order to completely describe the business logic og the game.


###Reserved Words
####NUM_PLAYERS()
Number of Players 

####CUR_TURN()
Function that returns the Current Turn Count 

####TOTAL_TURNS()
Current Turn Count 

####AVERAGE()
Function that calculates the average of a player variable over all players

####MIN(), MAX()
Function that calculates the minimum/maximum of a player variable over all players

####TURN_WORLD_VALUE(variable_name,turn_count)
Function that return the value of a world variable on a specific turn count. Return zero if the turn count is not yet reached in the game realization.

####PLAYER_VALUE(player_variable_name,turn_count,player_uuid)
Function that returns the value of a user variable

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
* 

##Communication with the presentation elayer (API annotations)
###/initGAME
POST variables: 
players[], array of uuids
