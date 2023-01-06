package org.rost.study;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Hello world!
 *
 */
public class Consumer {
    public static void main( String[] args ) throws JsonProcessingException {
        Translator translator = new Translator();
        System.out.println(translator.translate());
    }
}
