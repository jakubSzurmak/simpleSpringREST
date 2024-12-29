package pl.runnerData.shoe.function;

import org.springframework.stereotype.Component;
import pl.runnerData.shoe.dto.GetShoesResponse;
import pl.runnerData.shoe.entity.Shoe;
import java.util.List;
import java.util.function.Function;

@Component
public class ShoesToResponseFunction implements Function <List<Shoe>, GetShoesResponse>{
    @Override
    public GetShoesResponse apply(List<Shoe> entities) {
        return GetShoesResponse.builder()
                .shoes(entities.stream()
                        .map(shoe -> GetShoesResponse.Shoe.builder()
                                .id(shoe.getShoe_id())
                                .brandName(shoe.getBrandName())
                                .model(shoe.getModel())
                                .size(shoe.getSize())
                                .ownerId(shoe.getOwner().getId())
                                .build())
                        .toList())
                .build();
    }
}
