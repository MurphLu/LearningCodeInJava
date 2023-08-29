package org.dp.core.builder;

public class Main {
    public static void main(String[] args) {
        new Person.PersonBuilder().build();
    }

}

class Person {
    String name;
    int age;

    private Person(){}

    static class PersonBuilder{
        Person p = new Person();

        PersonBuilder buildBasic(String name){
            p.name = name;
            return this;
        }

        PersonBuilder name(String name){
            p.name = name;
            return this;
        }

        PersonBuilder age(int age){
            p.age = age;
            return this;
        }

        Person build() {
            return p;
        }
    }
}