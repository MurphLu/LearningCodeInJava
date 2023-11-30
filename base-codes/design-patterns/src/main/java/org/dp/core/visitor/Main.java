package org.dp.core.visitor;


/// 内部结构固定的情况下用 visitor
/// 多数用在编译器
public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer();
        PersonalVisitor pv = new PersonalVisitor();
        computer.accept(pv);
        System.out.println(pv.totalPrice);
    }
}

class Computer {
    ComputerPart cpu = new CPU();
    ComputerPart memory = new Memory();

    public void accept(Visitor v) {
        this.cpu.accept(v);
        this.memory.accept(v);
    }

}

interface ComputerPart{
    void accept(Visitor v);
    double getPrice();
}

class CPU implements ComputerPart {

    @Override
    public void accept(Visitor v) {
        v.visitsCPU(this);
    }

    @Override
    public double getPrice() {
        return 500;
    }
}

class Memory implements ComputerPart {
    @Override
    public void accept(Visitor v) {
        v.visitsMemory(this);
    }

    @Override
    public double getPrice() {
        return 100;
    }
}

interface Visitor {
    public void visitsCPU(CPU cpu);

    public void visitsMemory(Memory memory);
}


class PersonalVisitor implements Visitor {
    double totalPrice = 0;

    @Override
    public void visitsCPU(CPU cpu) {
        totalPrice += cpu.getPrice()*0.95;
    }

    @Override
    public void visitsMemory(Memory memory) {
        totalPrice += memory.getPrice()*0.95;
    }
}