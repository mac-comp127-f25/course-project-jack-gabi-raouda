# Fishing!

**Raouda Mamane Bello Boubacar, Jack Fang, Gabi Palladino**

## Project description

This is a fishing game, where the user moves a boat left and right across the scree using the mouse. By pressing the space bar, the player drops a fishing line to catch fish swimming below. Different types of fish give different point values which allows the player to keep track of their score as they play. Smaller fish are worth fewer points, while larger fish give higher scores. Catching a shark immediately ends the game. The ultimate goal is to catch all the fish while avoiding sharks. 

## Technical guide

### Technical requirements
To run this project, the user needs:
- Java 17 or Java 21, which is required for the Kilt Graphics library
- The Macalester Graphics (Kilt Graphics) library properly set up in their environment
- An IDE such as VS Code or the ability to run Java from the command line

### How to run the program
The main class of the program is 'FishingGame'. To run the Game:
1. Make sure all project files are compiled correctly 
2. Run the 'FishingGame' class
3. A game window will open where the player can start playing immediately 

## Acknowledgements and resources
We used the Macalester Graphics library provided for the course. Some image assets were sourced online and are credited below

### Image sources:
- Seaweed: https://www.clipartmax.com/max/m2i8b1H7G6K9d3K9/
- Bubbles: https://pngimg.com/image/69640
- Cloud: https://creazilla.com/media/clipart/3165364/clouds
- Fish 1 : https://www.exploringthedeep.com/creatures/colossal-squid/
- Fish 2 : https://www.fisheries.noaa.gov/new-england-mid-atlantic/science-data/acadian-redfish-qa-qc-exercise-results
- Fish 3 : https://www.freepik.com/premium-photo/whales_91952475.htm#from_element=cross_selling__photo 
- Fish 4 : https://verwijsseafood.com/product/tuna-saku-aaa-ivp-yf-5kg/ 
- Fish 5 : https://saveourseas.com/worldofsharks/species/grey-reef-shark
- Fish 6: https://www.aqueon.com/resources/care-guides/rainbowfish
- Fish 7: https://www.pngplay.com/image/111483


## Known issues:
The points don't affect the gameplay since the maximum number of points the user can get in one game depends on the random generation of types of fish, but it is still fun to see how high of a score you can get. The game also does not store high scores.

There is a glitch where sometimes if the fish swims close to the boat and touches the fishing line, it will get caught automatically. This is not much of a problem since it just gives you free points, but if a shark does it, it can be frustrating since it immediately ends the game. 

There is also a glitch where when the game ends, the fishing line stays as a small black square on the screen while the boat keeps moving. This does not affect gameplay, though. 

## Societal impact:
Our project is a simple game made mainly for fun, and it does not collect any user data or connect to outside systems. Because of this, there is very little risk of the game being used in harmful or malicious ways. The content of the game is also neutral and does not target or exclude any specific group of people, nor does it promote stereotypes or biased ideas.

However, the game does have some accessibility limitations. It relies heavily on visual elements and on using a mouse and keyboard, which could make it difficult for players with visual impairments or limited motor control to play. For example, someone who uses assistive technologies instead of a mouse might not be able to interact with the game easily. 

If we were to continue developing the project, we could improve accessibility by adding features such as keyboard-only controls or audio feedback.