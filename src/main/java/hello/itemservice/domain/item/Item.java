package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//제약이 많고 복잡해서 권장하지 않고, object 오류는 java로 코드를 짜서 검증하는걸 추천
//@ScriptAssert(lang="javascript",script = "_this.price * _this.quantity >= 10000", message = "총합이 10000원 넘게 입력해주세요")
public class Item {
    //V3-1
    /*@NotNull //수정 요구사항 추가
    private Long id;

    @NotBlank(message =  "공백x")
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    //@Max(9999) 수정 요구사항 추가
    private Integer quantity;*/

    //V3-2
    /*@NotNull(groups = UpdateCheck.class) //수정시에만 적용
    private Long id;

    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min = 1000, max = 1000000, groups = {SaveCheck.class,
            UpdateCheck.class})
    private Integer price;

    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = SaveCheck.class) //등록시에만 적용
    private Integer quantity;*/

    //V4
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
