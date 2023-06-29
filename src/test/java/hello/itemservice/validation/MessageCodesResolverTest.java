package hello.itemservice.validation;

import org.junit.jupiter.api.Test;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;

import static org.assertj.core.api.Assertions.assertThat;

public class MessageCodesResolverTest {

    MessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
    
    @Test
    void messageCodesResolverObject() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item");
        for(String messageCode : messageCodes){
            System.out.println("messageCode = " + messageCode);
        }
        assertThat(messageCodes).containsExactly("required.item", "required");
    }

    @Test
    void messageCodesResolverField() {
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "item", "itemName", String.class);
        for(String messageCode : messageCodes){
            System.out.println("messageCode = " + messageCode);
        }
        assertThat(messageCodes).containsExactly(
                "required.item.itemName",
                "required.itemName",
                "required.java.lang.String",
                "required"
        );
        //bindingResult.rejectValue("item", "required"); -> 내부에서 MessageCodesResolver을 사용함.
        //그래서 rejectValue()에 객체와 message code 맨 앞의 코드만 적어줘도 메세지를 찾을 수 있었음.
        //new FieldError("item", "itemName", null, false, messageCodes, null, null); -> FileError나 ObjectError에 규칙대로 생성된 messageCodes 배열을 보내주면
        //순서대로 메세지를 찾을 수 있음.
        //assertThat(messageCodes).containsExactly("required.item", "item");
    }
}
