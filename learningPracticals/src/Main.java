class A {

    void method() {

        System.out.println("Class A");

    }

}

class B extends A {

    void method() {

        super.method();

        System.out.println("Class B");

    }

}

public class Main {

    public static void main(String[] args) {

        B obj = new B();

        obj.method();

    }

}
