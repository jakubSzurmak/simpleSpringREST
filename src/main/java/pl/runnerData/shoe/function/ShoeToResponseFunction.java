package pl.runnerData.shoe.function;

import org.springframework.stereotype.Component;
import pl.runnerData.shoe.dto.GetShoeResponse;
import pl.runnerData.shoe.entity.Shoe;

import java.util.function.Function;

@Component
public class ShoeToResponseFunction implements Function<Shoe, GetShoeResponse> {

    @Override
    public GetShoeResponse apply(Shoe entity) {
        return GetShoeResponse.builder()
                .id(entity.getShoe_id())
                .brandName(entity.getBrandName())
                .model(entity.getModel())
                .color(entity.getColor())
                .size(entity.getSize())
                .ownerId(entity.getOwner().getId())
                .build();
    }
}
