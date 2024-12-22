package MyRunner;

import MyRunner.function.DataInitializator;
import MyRunner.shoe.dto.ShoeDTO;
import MyRunner.shoe.entity.Shoe;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DataInitializator di = new DataInitializator();

        System.out.println("\n Task 2 \n");
        List<Object> container = di.startingDataState();
        for (Object runner : container) {
            System.out.println(runner.toString());
        }

        System.out.println("\n Task 3 \n");
        di.printAllShoes(di.gatherAllDistinctShoes());

        System.out.println("\n Task 4 \n");
        Collection<Object> sorted = (di.getShoeList()).stream()
                .filter(Objects::nonNull).filter(i -> ((Shoe) i)
                        .getColor().equals("Black")).collect(Collectors.toList());
        di.printAllShoes(sorted);

        System.out.println("\n Task 5 \n");
        Collection<Object> sortedDTO = di.getShoeList().stream()
                .filter(Objects::nonNull)
                .map(i -> ShoeDTO.builder().brandName(((Shoe)i).getBrandName())
                        .model(((Shoe)i).getModel())
                        .color(((Shoe)i).getColor())
                        .size(((Shoe)i).getSize())
                        .ownerID(((Shoe)i).getOwner().getId())
                        .build())
                .sorted()
                .collect(Collectors.toList());
        di.printAllShoes(sortedDTO);

        System.out.println("\n Task 6 \n");
        /*Serialization*/
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("collection.bin"))) {
            oos.writeObject(di.getContainer());
            System.out.println("Collection has been serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*DeSerialization*/
        Collection<Object> deserializedCollection;
        try (FileInputStream fis = new FileInputStream("collection.bin");
             ObjectInputStream in = new ObjectInputStream(fis)) {
            deserializedCollection = (Collection<Object>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        di.printAllShoes(deserializedCollection);

        System.out.println("\n Task 7 \n");
        ForkJoinPool shoesThreadPool = new ForkJoinPool(1);
        ForkJoinPool runnersThreadPool = new ForkJoinPool(1);
        try {
            runnersThreadPool.submit(() -> DataInitializator.runParallel(di.getContainer(), "Runners")).get();
            shoesThreadPool.submit(() -> DataInitializator.runParallel(di.getShoeList(), "Shoes")).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            shoesThreadPool.shutdown();
            runnersThreadPool.shutdown();
        }
    }
}
