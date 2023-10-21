import org.projog.api.Projog;
import org.projog.api.QueryResult;

import java.io.File;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Projog projog = new Projog();
        projog.consultFile(new File("src/main/resources/recipes.pl"));
        QueryResult r1;
        System.out.println("It is an console interface which connects Prolog knowledge ");
        System.out.println("base with Java. This knowledge base stores data about ingredients");
        System.out.println("and recipes.");
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("Do you want to:");
            System.out.println("Type '1' to know what you can cook with specific ingredients");
            System.out.println("Type '2' to Know which ingredients you need to cook a dish");
            System.out.println("Type 'exit' to shut down the program");
            input = scanner.next();
            if(Objects.equals(input, "1")){
                System.out.println("Ingredients list: ");
                int counter = 0;
                r1 = projog.executeQuery("ingredient(X).");
                if(r1.next()) System.out.print(r1.getTerm("X"));
                if(!r1.isExhausted()) while(r1.next()){
                    counter++;
                    System.out.print(", ");
                    if(counter>=6){
                        System.out.println();
                        counter = 0;
                    }
                    System.out.print(r1.getTerm("X"));
                }
                counter = 0;
                System.out.println();
                System.out.println("Type ingredients, one by one, which you want to use, pressing 'Enter' button after each one.");
                System.out.println("When finished, type 'go'");
                StringBuilder term = new StringBuilder("[");
                input = scanner.next();
                if(!Objects.equals(input, "go")) {
                    term.append(input);
                    while (true) {
                        input = scanner.next();
                        if (!Objects.equals(input, "go"))
                            term.append(", ").append(input);
                        else break;
                    }
                }
                term.append("]");
                r1 = projog.executeQuery("ingredient_of("+term+", X).");
                System.out.println("Receipts: ");
                if(r1.next()) System.out.print(r1.getTerm("X"));
                if(!r1.isExhausted()) {
                    while (r1.next()) {
                        counter++;
                        System.out.print(", ");
                        if (counter >= 6) {
                            System.out.println();
                            counter = 0;
                        }
                        System.out.print(r1.getTerm("X"));
                    }
                }
                else System.out.println("No receipts found");

                System.out.println();
                input = "";
            }
            if(Objects.equals(input, "2")){
                System.out.println("Receipts list: ");
                int counter = 0;
                r1 = projog.executeQuery("dish(X).");
                if(r1.next()) System.out.print(r1.getTerm("X"));
                if(!r1.isExhausted()) while(r1.next()){
                    counter++;
                    System.out.print(", ");
                    if(counter>=6){
                        System.out.println();
                        counter = 0;
                    }
                    System.out.print(r1.getTerm("X"));
                }
                counter = 0;
                System.out.println();
                System.out.println("Type dish which you want to cook.");
                input = scanner.next();
                r1 = projog.executeQuery("ingredients_of(X, "+input+"), list_member(A, X).");
                System.out.println("Ingredients: ");
                if(r1.next()) {
                    System.out.print(r1.getTerm("A"));
                    if (!r1.isExhausted()) while (r1.next()) {
                        counter++;
                        System.out.print(", ");
                        if (counter >= 6) {
                            System.out.println();
                            counter = 0;
                        }
                        System.out.print(r1.getTerm("A"));
                    }
                }
                else System.out.println("Receipt is unknown");
                input = "";
                System.out.println();
            }
        } while (!Objects.equals(input, "exit"));
    }
}
