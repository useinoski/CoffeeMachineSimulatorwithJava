package machine;
import java.util.Scanner;

public class CoffeeMachine {
    protected static int waterInMachine = 400;
    protected static int milkInMachine = 540;
    protected static int beansInMachine = 120;
    protected static int disposableCups = 9;
    protected static int balance = 550;
    protected static int coffeesMade = 0;
    protected static final int CLEANING_LIMIT = 10;

    enum Coffee {
        ESPRESSO("1", 250, 0, 16, 4),
        LATTE("2", 350, 75, 20, 7),
        CAPPUCCINO("3", 200, 100, 12, 6);

        private final String option;
        private final int water;
        private final int milk;
        private final int beans;
        private final int price;

        Coffee(String option, int water, int milk, int beans, int price) {
            this.option = option;
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.price = price;
        }

        static Coffee fromOption(String option) {
            for (Coffee coffee : Coffee.values()) {
                if (coffee.option.equals(option)) {
                    return coffee;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        startMachine();
    }

    public static void buy(){
        Scanner sc = new Scanner(System.in);

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:\n");
        String choice = sc.next();

        if ("back".equals(choice)) {
            return;
        }

        Coffee coffee = Coffee.fromOption(choice);
        if (coffee == null) {
            return;
        }

        if (waterInMachine < coffee.water) {
            System.out.println("Sorry, not enough water!");
        } else if (milkInMachine < coffee.milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (beansInMachine < coffee.beans) {
            System.out.println("Sorry, not enough beans!");
        } else if (disposableCups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            waterInMachine -= coffee.water;
            milkInMachine -= coffee.milk;
            beansInMachine -= coffee.beans;
            disposableCups--;
            balance += coffee.price;
            coffeesMade++;
        }
    }

    public static void clean() {
        coffeesMade = 0;
        System.out.println("I have been cleaned!");
    }

    public static void fill(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        int waterMl = sc.nextInt();
        waterInMachine += waterMl;
        System.out.println("Write how many ml of beans you want to add:");
        int milkMl = sc.nextInt();
        milkInMachine += milkMl;
        System.out.println("Write how many grams of coffee beans you want to add: ");
        int beansGm = sc.nextInt();
        beansInMachine += beansGm;
        System.out.println("Write how many disposable cups you want to add: ");
        int disposableCp = sc.nextInt();
        disposableCups += disposableCp;

    }

    public static void take(){
        System.out.printf("I gave you %d\n", balance );
        balance -= balance;


    }

    public static void remaining(){
        System.out.printf("The coffee machine has:\n" +
                "%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans\n" +
                "%d disposable cups\n" +
                "%d of money\n",waterInMachine,milkInMachine,beansInMachine,disposableCups,balance);
    }

    public static void startMachine(){
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("Write action (buy, fill, take , clean ,remaining, exit): ");
            String action = sc.next();

            switch (action) {
                case "buy":
                    if (coffeesMade >= CLEANING_LIMIT) {
                        System.out.println("I need cleaning!");
                    } else {
                        buy();
                    }
                    break;
                case "fill":
                    fill();
                    break;
                case "take":
                    take();
                    break;
                case "clean":
                    clean();
                    break;
                case "remaining":
                    remaining();
                    break;
                case "exit":
                    exit = true;
                    break;

            }
        }
    }






}
