import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ClientApplication {
    final static ArrayList<Dish> dishesMock = new ArrayList<Dish>(Arrays.asList(
            new Dish("z", 100, 10, 100),
            new Dish("a", 100, 1000, 100),
            new Dish("wether", 100, 1, 100),
            new Dish("taco", 100, 20, 100),
            new Dish("dragon fruit", 100, 50, 100),
            new Dish("a", 100, 670, 100),
            new Dish("a", 100, 670, 100)
    ));

    public static void main(String ...args) throws IOException, NamingException {
        Scanner scanner = new Scanner(System.in);

        // System.out.println("Enter input file");
        String inputFile = "in.json"; // scanner.nextLine();

        // System.out.println("Enter output file");
        String outputFile = "out.json"; // scanner.nextLine();

        Dishes dishes = new Dishes(dishesMock);
        dishes.readFromJSON(new File(inputFile));

        Context context = new InitialContext();
        SortingService sortingService = (SortingService) context.lookup("rmi://localhost/dishes");
        Dishes sortedAndFilteredDishes = sortingService.sortAndFilterUniqueItems(dishes);
        sortedAndFilteredDishes.writeToJSON(new File(outputFile));
    }
}
