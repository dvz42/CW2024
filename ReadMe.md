# SkyBattle Game

## Github Link
[SkyBattle Game Repository](https://github.com/dvz42/CW2024)



## Implemented and Working Features

### Horizontal Movement
- **Description**: Added the ability for the player's plane to move horizontally.
- **Implementation**: Modified the `handleKeyPressed` and `handleKeyReleased` methods in `LevelParent` to include horizontal movement controls.

### Changed .jpg to .png in Shield Class
- **Description**: Updated the image format for the shield.
- **Implementation**: Modified the `ShieldImage` class to use `.png` files instead of `.jpg`.

### Added Removing Bullet When They Go Out of Bounds
- **Description**: Bullets are now removed when they go out of the screen bounds.
- **Implementation**: Updated the `removeDestroyedBullets` method in `ActorManager` to check for out-of-bounds bullets.

### Added Max Number of Bullets for Reducing Memory Utilization
- **Description**: Limited the number of bullets to reduce memory usage.
- **Implementation**: Implemented a maximum bullet count check in the `fireProjectile` method of the `UserPlane` class.

### Fixed Infinite Alerts on Error
- **Description**: Prevented infinite alert dialogs from appearing in case of an error.
- **Implementation**: Modified the `showErrorAlert` method in `Controller` to ensure only one alert is shown, as well as a stop function for the timeline.

### Fixed Enemies Going Out of Bounds Increasing Kill Counts
- **Description**: Prevented enemies from increasing kill counts when they go out of bounds.
- **Implementation**: Updated the `handleEnemyPenetration` method in `CollisionManager` to correctly handle out-of-bounds enemies.

### Showing Boss Health
- **Description**: Displayed the boss's health on the screen.
- **Implementation**: Added a `showBossHealth` method in `LevelFourView` and updated the `updateActors` method in `LevelFour` to refresh the boss's health display.

### Fixed Enemies Spawning More Than the Declared Number
- **Description**: Ensured that the number of enemies spawned does not exceed the declared limit.
- **Implementation**: Adjusted the `spawnEnemyUnits` method in `LevelParent` to respect the enemy limit.

## Implemented but Not Working Properly

### Boss Shield Not Turning Off
- **Description**: The boss's shield does not deactivate as expected.
- **Current Status**: Needs further debugging and adjustments in the `Boss` class.

## Not Implemented

### Resizable Screens
- **Description**: The game does not support resizable screens.
- **Current Status**: Feature not yet implemented, effort was made however it did not yield good results so it was scratched off.

### JUnit Tests
- **Description**: As the game is made in JavaFX the junit tests throws a "Graphics not initialized error"

## Refactoring

### Grouped into Packages
- **Description**: Organized the code into packages for better structure.
- **Implementation**: Created packages for `actors`, `levels`, `controller`, `managers`, `projectiles`, `utils`, and `views`.

### Implemented Factory Pattern for Levels
- **Description**: Used the Factory pattern to create level instances.
- **Implementation**: Created a `LevelFactory` class to handle level creation.

### Refactored LevelParent into Separate Manager Classes
- **Description**: Split the `LevelParent` class into multiple manager classes for better single responsibility.
- **Implementation**: Created separate manager classes for `ActorManager`, `BackgroundManager`, `GameStateManager`, `GameTimelineManager`, and `CollisionManager`.

## New Classes

### CollisionManager
- **Description**: Manages collision detection between actors.
- **Implementation**: Handles collisions and updates actor states accordingly.

### GameStateManager
- **Description**: Manages the game state transitions.
- **Implementation**: Observes and notifies changes in the game state.

### BackgroundManager
- **Description**: Manages the game background and user input.
- **Implementation**: Initializes the background and handles key events.

### GameTimelineManager
- **Description**: Manages the game loop and timeline.
- **Implementation**: Uses a `Timeline` to update the game scene at regular intervals.

### ActorManager
- **Description**: Manages the actors in the game.
- **Implementation**: Adds, updates, and removes actors from the game.

### LevelThree
- **Description**: Represents the third level of the game.
- **Implementation**: Extends `LevelParent` and implements level-specific logic.

### LevelFour
- **Description**: Represents the fourth level of the game.
- **Implementation**: Extends `LevelParent` and implements level-specific logic.

## Renamed Classes

### LevelTwo -> LevelFour
- **Description**: Renamed `LevelTwo` to `LevelFour` for better clarity.
- **Implementation**: Updated all references to `LevelTwo` to `LevelFour`.

### LevelViewLevelTwo -> LevelFourView
- **Description**: Renamed `LevelViewLevelTwo` to `LevelFourView` for better clarity.
- **Implementation**: Updated all references to `LevelViewLevelTwo` to `LevelFourView`.

### Main -> SkyBattleApplication
- **Description**: Renamed `Main` to `SkyBattleApplication` for better clarity.
- **Implementation**: Updated all references to `Main` to `SkyBattleApplication`.

## Modified Java Classes

### LevelParent
- **Description**: Added horizontal movement and removed out-of-bounds bullets.
- **Implementation**: Updated the `handleKeyPressed`, `handleKeyReleased`, and `removeDestroyedBullets` methods.

### Controller
- **Description**: Stopped infinite alerts from being created in case of an error.
- **Implementation**: Modified the `showErrorAlert` method to ensure only one alert is shown.

### Boss
- **Description**: Added boss health display.
- **Implementation**: Updated the `Boss` class to include health management and display logic.

### Level Classes
- **Description**: Fixed enemy spawning rate.
- **Implementation**: Adjusted the `spawnEnemyUnits` method to control the spawning frequency.

## Unexpected Problems

### Enemies Going Out of Bounds Increasing Kill Counts
- **Description**: Enemies going out of bounds increased kill counts and allowed the player to transition to another level.
- **Solution**: Updated the `handleEnemyPenetration` method in `CollisionManager` to correctly handle out-of-bounds enemies.

## Level Additions

### Added Two Levels
- **Description**: Added two new levels to the game.
- **Implementation**: Created `LevelThree` and `LevelFour` classes extending `LevelParent`.

## Compilation Instructions
### Prerequisites
   Java, Maven, Javafx
1. *Clone the Repository*:
   git clone https://github.com/dvz42/CW2024
   cd CW2024
2. *Run the Project*:
   mvn javafx:run
