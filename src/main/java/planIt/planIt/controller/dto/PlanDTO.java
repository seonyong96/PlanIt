package planIt.planIt.controller.dto;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.MapKeyColumn;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDTO {

    @NotNull(message = "일정을 입력해주세요.")
    @ElementCollection
    @CollectionTable(name = "plan_description")
    @MapKeyColumn(name = "description_key")
    @Column(name = "description_value")
    private Map<Integer, String> description;

    private String target_date;

    private Long userId;

}
