package org.rost.study;

/**
 * Hello world!
 *
 */
public class Consumer {
    public static void main( String[] args ) {
        Translator translator = new Translator();
        System.out.println(translator.translateWithJacksonMapping());
    }
}
