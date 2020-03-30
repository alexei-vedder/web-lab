import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ClientApplication {
    private final static ArrayList<Dish> dishesMock = new ArrayList<>(Arrays.asList(
            new Dish("z", 100, 10),
            new Dish("a", 100, 1000),
            new Dish("wether", 100, 1),
            new Dish("taco", 100, 20),
            new Dish("dragon fruit", 100, 50),
            new Dish("a", 100, 670),
            new Dish("a", 100, 670)
    ));

    private static void createMock(File file) throws IOException {
        new Ration(dishesMock).writeToJSON(file);
    }

    public static void main(String ...args) throws IOException, NamingException {
        Scanner scanner = new Scanner(System.in);

        // System.out.println("Enter input file");
        String inputFilePath = "in.json"; // scanner.nextLine();

        // System.out.println("Enter output file");
        String outputFilePath = "out.json"; // scanner.nextLine();

        createMock(new File(inputFilePath));

        Ration ration = new Ration();
        ration.readFromJSON(new File(inputFilePath));

        Context context = new InitialContext();
        SortingService sortingService = (SortingService) context.lookup("rmi://localhost/dishes");
        Ration sortedAndFilteredRation = sortingService.sortAndFilterUniqueItems(ration);
        sortedAndFilteredRation.writeToJSON(new File(outputFilePath));
    }
}
