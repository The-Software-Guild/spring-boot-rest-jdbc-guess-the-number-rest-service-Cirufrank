/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.we.guessthenumber.data;

import com.we.guessthenumber.model.Round;
import java.time.LocalDateTime;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 *
 * @author Cirũ Franklin (she/they), Software Engineer
 * @course DI002 Full Stack Development Using Java and React (2210)
 * @project Assessment: Guess the Number REST Service
 * 
 * @description This class is our parameter resolver for the 
 * Round class, and it allows us to use dependency injection within our unit tests so that we
 * do not have to continuously instantiate a Round
 * object before each test is ran
 */


public class RoundParameterResolver implements ParameterResolver {
    final Round roundToAdd = new Round(1, 1, 
            1010, LocalDateTime.now(), "e:1:p:2");
    
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType() == Round.class;
    }
    
    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        return roundToAdd;
    }
}
