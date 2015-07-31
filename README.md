# SIMBUG, Simulation Business Game Framework

##What is SIMBUG
A SIMBUG is an acronym for a *Simulation Business Game*. SIMBUG is related to [serious or applied games](https://en.wikipedia.org/wiki/Serious_game), i.e. games designed for a primary purpose other than pure entertainment and are used to enhance learning in business education.

Creating a simulation business game should follow several educational design principles but also requires several computing and programming skills. We want to seperate those two concerns and give the ability to educators all over the world to focus on their main skill, i.e designing the game. 

SIMBUG is actually all the subsystems included in red line in the following figure.

![simbug_architecture](https://cloud.githubusercontent.com/assets/13544631/9016385/802dad20-37d9-11e5-9734-c3f99318b96b.png)

So, in terms of the [three-tier architecture](https://en.wikipedia.org/wiki/Multitier_architecture), SIMBUG is composed of **structural static elements** (business logic and data layer) and of a **dynamic aspect** concering the communication of the game's business logic with the presentation layer (the API).

Hence the educator will be responsible for providing the "business logic" of the game through an easy-to-write definition file. He will be also responsible for designing and implementing the presentation layer (e.g. the web forms that the players will complete), although in the future a default presentation layer will be offered. Then the Restful server will be responsible for implementing the game's business logic and will also cater the data storage needs that actually depend on the former. 

An [open standard](https://github.com/dkremmydas/simbug/tree/master/Open-Standard) will be made publicly available and an open source JAVA RESTful server will be crafted.


##Acknowledgments
SIMBUG was initiated by an [ELLAAK](https://ellak.gr/) funded project. The original project was consisted of three work-packages

1. Compiling a computerized business games open standard
2. Developing a RESTful server that serves SIMBUGs, following the developed standard
3. Writing a proof-of-concept game that rests in a client machine and communicates with the server
