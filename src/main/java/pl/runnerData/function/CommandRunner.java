package pl.runnerData.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.runnerData.initialize.DataInitializator;
import pl.runnerData.runner.service.DefaultRunnerService;
import pl.runnerData.shoe.entity.Shoe;
import pl.runnerData.shoe.service.DefaultShoeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


@Component
public class CommandRunner implements CommandLineRunner {
    private final DefaultRunnerService defaultRunnerService;
    private final DefaultShoeService defaultShoeService;
    private boolean end = false;

    @Autowired
    public CommandRunner(DefaultRunnerService defaultRunnerService, DefaultShoeService defaultShoeService) {
        this.defaultRunnerService = defaultRunnerService;
        this.defaultShoeService = defaultShoeService;
        List<Object> container = new ArrayList<>();
        List<Object> shoeList = new ArrayList<>();
        DataInitializator.dataInitializator(container, shoeList, defaultRunnerService, defaultShoeService);
    }

    String startMessage =
            """
                    
                    list_r                 - display Runners
                    list_s                 - display shoes
                    add                    - adds new shoes to a specific runner
                    remove                 - remove shoe by id
                    stop                   - stops application
                    """;

    void addShoe(Scanner scanner){
        this.listRunners();
        System.out.println("Desired ID: ");
        String uuid = scanner.nextLine();
        System.out.println("Brand: ");
        String brandName = scanner.nextLine();

        System.out.println("Model: ");
        String model = scanner.nextLine();

        System.out.println("Color: ");
        String color = scanner.nextLine();
        System.out.println("Size: ");
        double size = Double.parseDouble(scanner.nextLine());
        System.out.println("Owner's id: ");
        String owner = scanner.nextLine();
        //adding to a spec category

        defaultShoeService.create(Shoe.builder().shoe_id(UUID.fromString(uuid)).brandName(brandName)
                .model(model).color(color).size(size)
                .owner(defaultRunnerService.findById(UUID.fromString(owner)).orElse(null)).build());

        System.out.println("Shoe" + uuid + "Added");
    }

    void removeShoe(Scanner scanner){
        System.out.println("Desired ID: ");
        String uuid = scanner.next();

        defaultShoeService.delete(UUID.fromString(uuid));
        System.out.println("Shoe" + uuid + "Removed");
    }

    void listShoes(){
        System.out.println("[Brand, Model, Size, \nOwner]");
        defaultShoeService.findAll().forEach(shoe -> System.out.println(shoe.toString()));
    }

    void listRunners(){
        System.out.println("[Runners_ID, Name, Surname, Date of Birth]");
        defaultRunnerService.findAll().forEach(System.out::println);
    }


    @Override
    public void run(String... args){
        Scanner scanner = new Scanner(System.in);
        System.out.println(startMessage);
        while (!end) {
            String command = scanner.nextLine();
            switch (command) {
                case "stop":
                    end = true;
                    System.exit(0);
                    break;
                case "list_r":
                    listRunners();
                    System.out.println(startMessage);
                    break;
                case "list_s":
                    listShoes();
                    System.out.println(startMessage);
                    break;
                case "add":
                    addShoe(scanner);
                    System.out.println(startMessage);
                    break;
                case "remove":
                    removeShoe(scanner);
                    System.out.println(startMessage);
                    break;
            }
        }
    }
}
