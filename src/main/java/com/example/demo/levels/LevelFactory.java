package com.example.demo.levels;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.example.demo.utils.GameState;

/**
 * Factory class responsible for creating instances of levels based on the game state.
 */
public class LevelFactory {

    private static final Map<GameState, String> LEVEL_MAP = Map.of(
            GameState.LEVEL_ONE, "com.example.demo.levels.LevelOne",
            GameState.LEVEL_TWO, "com.example.demo.levels.LevelTwo",
            GameState.LEVEL_THREE, "com.example.demo.levels.LevelThree",
            GameState.LEVEL_FOUR, "com.example.demo.levels.LevelFour");

    /**
     * Creates an instance of a level based on the specified game state.
     *
     * @param state the game state representing the level to be created
     * @param screenHeight the height of the screen
     * @param screenWidth the width of the screen
     * @return an instance of LevelParent representing the created level
     * @throws ClassNotFoundException if the level class is not found
     * @throws NoSuchMethodException if the level class does not have the expected constructor
     * @throws SecurityException if there is a security violation
     * @throws InstantiationException if the level class cannot be instantiated
     * @throws IllegalAccessException if the level class or its constructor is not accessible
     * @throws IllegalArgumentException if the level class constructor arguments are invalid
     * @throws InvocationTargetException if the level class constructor throws an exception
     */
    public LevelParent createLevel(GameState state, double screenHeight, double screenWidth)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String className = LEVEL_MAP.get(state);
        if (className == null) {
            return null;
        }

        Class<?> myClass = Class.forName(className);
        Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
        return (LevelParent) constructor.newInstance(screenHeight, screenWidth);
    }
}