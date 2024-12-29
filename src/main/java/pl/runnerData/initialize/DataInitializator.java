package pl.runnerData.initialize;

import org.springframework.stereotype.Component;
import pl.runnerData.function.RandomBirthdateGenerator;
import pl.runnerData.runner.entity.Runner;
import pl.runnerData.runner.service.DefaultRunnerService;
import pl.runnerData.shoe.entity.Shoe;
import lombok.Getter;
import pl.runnerData.shoe.service.DefaultShoeService;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


@Getter
@Component
public class DataInitializator{

    static List<String> shoeBrands = List.of(
            "Nike",
            "Adidas",
            "Asics",
            "Puma",
            "New Balance",
            "Brooks",
            "Hoka One One",
            "Under Armour",
            "Saucony",
            "Mizuno"
    );

    static List<String> shoeModels = List.of(
            "Air Zoom Pegasus 39",
            "Ultraboost 22",
            "Gel-Kayano 29",
            "Deviate Nitro 2",
            "Fresh Foam 1080v12",
            "Ghost 15",
            "Clifton 9",
            "HOVR Machina 3",
            "Endorphin Speed 3",
            "Wave Rider 26"
    );

    static List<String> names = List.of(
            "Jan",
            "Arkadiusz",
            "Tomasz"
    );

    static List<String> surnames = List.of(
            "Kowalski",
            "Tomala",
            "Guzik"
    );

    static List<String> colors = List.of(
            "Red",
            "Green",
            "Blue",
            "Black",
            "White",
            "Grey"
    );
    static List<String> runner_uuids = List.of(
            "c4804e0f-769e-4ab9-9ebe-0578fb4f00a6",
            "81e1c2a9-7f57-439b-b53d-6db88b071e4e",
            "ed6cfb2a-cad7-47dd-9b56-9d1e3c7a4197"
    );

    static List<String> shoe_uuids = List.of(
            "f5875513-bf7b-4ae1-b8a5-5b70a1b90e76",
            "5d1da2ae-6a14-4b6d-8b4f-d117867118d4",
            "2d9b1e8c-67c5-4188-a911-5f064a63d8cd",
            "525d3e7b-bb1f-4c13-bf17-926d1a12e4c0",
            "cc0b0577-bb6f-45b7-81d6-3db88e6ac19f",
            "cc0b0577-bb6f-45b7-81d6-3db88e6ac20f",
            "cc0b0577-bb6f-45b7-81d6-3db88e6ac21f",
            "cc0b0577-bb6f-45b7-81d6-3db88e6ac22f",
            "cc0b0577-bb6f-45b7-81d6-3db88e6ac23f",
            "cc0b0577-bb6f-45b7-81d6-3db88e6ac24f",
            "cc0b0577-bb6f-45b7-81d6-3db88e6ac25f",
            "cc0b0577-bb6f-45b7-81d6-3db88e6ac26f",
            "cc0b0577-bb6f-45b7-81d6-3db88e6ac27f"
    );


    public static void dataInitializator(List<Object> container, List<Object> shoeList,
                                          DefaultRunnerService defaultRunnerService,
                                          DefaultShoeService defaultShoeService) {
        List<Shoe> box = new ArrayList<>();
        for (short i = 0; i < 3; i++) {
            Runner newRunner = Runner.builder()
                    .id(UUID.fromString(runner_uuids.get(i)))
                    .name(names.get(i))
                    .surname(surnames.get(i))
                    .birthDate(RandomBirthdateGenerator.getRandomBirthdate())
                    .shoes(null).build();
            container.add(newRunner);
            defaultRunnerService.create(newRunner);
            box.clear();
            for (short j = 0; j < 5; j++) {
                Shoe newShoe = Shoe.builder().shoe_id(UUID.fromString(shoe_uuids.get(j+(i*4))))
                        .brandName(shoeBrands.get(j))
                        .model(shoeModels.get(j))
                        .color(colors.get(j))
                        .size((ThreadLocalRandom.current().nextDouble(35, 46)))
                        .owner((Runner) (container.get(i))).build();
                box.add(newShoe);
                shoeList.add(newShoe);
                defaultShoeService.create(newShoe);
            }
            Runner x = (Runner) container.get(i);
            (x).setShoes(box);
        }
    }


}
