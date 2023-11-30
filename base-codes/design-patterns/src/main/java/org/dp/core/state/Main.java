package org.dp.core.state;

// state 模式，如果operation 不变，那么可以用 state 模式
// 如果状态有限，不会扩展，那么直接 Switch case 即可

public class Main {
}

abstract class PersonState {
    abstract void smile();
}

class HappyState extends PersonState{

    @Override
    void smile() {
        System.out.println("happy state");
    }
}

class Person {
    String name;
    PersonState state;

    public void smile() {
        state.smile();
    }
}