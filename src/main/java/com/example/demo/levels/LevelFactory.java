package com.example.demo.levels;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.example.demo.levels.LevelParent;
import com.example.demo.utils.GameState;

public class LevelFactory {

    private static final Map<GameState, String> LEVEL_MAP = Map.of(
            GameState.LEVEL_ONE, "com.example.demo.levels.LevelOne",
            GameState.LEVEL_TWO, "com.example.demo.levels.LevelTwo",
            GameState.LEVEL_THREE, "com.example.demo.levels.LevelThree",
            GameState.LEVEL_FOUR, "com.example.demo.levels.LevelFour");

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