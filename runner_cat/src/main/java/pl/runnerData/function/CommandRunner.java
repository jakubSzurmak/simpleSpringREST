package pl.runnerData.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.runnerData.initialize.DataInitializator;
import pl.runnerData.runner.entity.Runner;
import pl.runnerData.runner.service.DefaultRunnerService;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;


@Component
public class CommandRunner implements CommandLineRunner {
    private final DefaultRunnerService defaultRunnerService;


    @Autowired
    public CommandRunner(DefaultRunnerService defaultRunnerService) {
        this.defaultRunnerService = defaultRunnerService;

    }

    String startMessage =
            """
                    
                    list_r                 - display Runners
                    add_r                  - adds new runner
                    remove_r               - remove runner by id
                    stop                   - stops application
                    """;


    void listRunners(){
        System.out.println("[Runners_ID, Name, Surname, Date of Birth]");
        defaultRunnerService.findAll().forEach(System.out::println);
    }

    void addRunner(Scanner scanner){
        UUID uuid = UUID.randomUUID();
        System.out.println("Name: ");
        String name = scanner.nextLine();

        System.out.println("Surname: ");
        String surname = scanner.nextLine();

        System.out.println("Birth date [YYYY-MM-DD]: ");
        String date = scanner.nextLine();

        defaultRunnerService.create(Runner
                .builder()
                .id(UUID.randomUUID())
                .name(name)
                .surname(surname)
                .birthDate(LocalDate.parse(date))
                .build());

        System.out.println("Runner: " + uuid + " Added\n");
        System.out.println(startMessage);
    }

    void removeRunner(Scanner scanner){
        System.out.println("Desired ID: ");
        String uuid = scanner.next();

        defaultRunnerService.delete(UUID.fromString(uuid));
        System.out.println("Runner: " + uuid + " Removed" + "\n");
        System.out.println(startMessage);
    }

    @Override
    public void run(String... args){
        DataInitializator.dataInitializator(defaultRunnerService);
        boolean end = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println(startMessage);
        while (!end) {
            String command = scanner.nextLine();
            switch (command) {
                case "stop":
                    end = true;
                    break;
                case "add_r":
                    addRunner(scanner);
                    break;
                case "remove_r":
                    removeRunner(scanner);
                    break;
                case "list_r":
                    listRunners();
                    System.out.println(startMessage);
                    break;
            }
        }
    }
}
