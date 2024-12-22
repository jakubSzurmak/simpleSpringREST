package MyRunner.function;

import MyRunner.runner.entity.Runner;
import MyRunner.shoe.entity.Shoe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.PrintStream;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class DataInitializator {
    private final List<Object> shoeList = new ArrayList();
    private final List<Object> container = new ArrayList();


    public List<Object> startingDataState() {
        List<String> shoeBrands = List.of("Nike", "Adidas", "Asics", "Puma", "New Balance", "Brooks", "Hoka One One", "Under Armour", "Saucony", "Mizuno");
        List<String> shoeModels = List.of("Air Zoom Pegasus 39", "Ultraboost 22", "Gel-Kayano 29", "Deviate Nitro 2", "Fresh Foam 1080v12", "Ghost 15", "Clifton 9", "HOVR Machina 3", "Endorphin Speed 3", "Wave Rider 26");
        List<String> names = List.of("Jan", "Arkadiusz", "Tomasz");
        List<String> surnames = List.of("Kowalski", "Tomala", "Guzik");
        List<String> colors = List.of("Red", "Green", "Blue", "Black", "White", "Grey");
        List<String> uuids = List.of("c4804e0f-769e-4ab9-9ebe-0578fb4f00a6", "81e1c2a9-7f57-439b-b53d-6db88b071e4e", "ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197");
        List<Shoe> box = new ArrayList();

        for(short i = 0; i < 3; ++i) {
            Runner newRunner = Runner.builder().id(UUID.fromString((String)uuids.get(i))).name((String)names.get(i)).surname((String)surnames.get(i)).birthDate(RandomBirthdateGenerator.getRandomBirthdate()).shoes((List)null).build();
            this.container.add(newRunner);
            box.clear();

            for(short j = 0; j < 5; ++j) {
                Shoe newShoe = Shoe.builder().brandName(shoeBrands.get(j)).model(shoeModels.get(j))
                        .color(colors.get(j)).size(ThreadLocalRandom.current().nextDouble(35.0F, 46.0F))
                        .owner((Runner)container.get(i)).build();
                box.add(newShoe);
                this.shoeList.add(newShoe);
            }

            Runner x = (Runner)this.container.get(i);
            x.setShoes(box);
        }

        return this.container;
    }

    public Set<Object> gatherAllDistinctShoes() {
        return (Set) Stream.of(this.getShoeList()).collect(Collectors.toSet());
    }

    public void printAllShoes(Collection<Object> collection) {
        PrintStream var10001 = System.out;
        Objects.requireNonNull(var10001);
        collection.forEach(var10001::println);
    }

    public static void runParallel(Collection<Object> collection, String name) {
        collection.parallelStream().forEach((o) -> {
            try {
                System.out.println(Thread.currentThread().getName() + " Category: " + name + "\nElement: " + o);
                Thread.sleep(1250L);
            } catch (InterruptedException var3) {
                Thread.currentThread().interrupt();
            }

        });
    }
}
