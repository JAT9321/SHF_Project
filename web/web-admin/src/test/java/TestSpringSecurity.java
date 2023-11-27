import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestSpringSecurity {
    @Test
    public void testPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String result1 = passwordEncoder.encode("123456");
        System.out.println(result1);
        String result2 = passwordEncoder.encode("123456");
        System.out.println(result2);
        String result3 = passwordEncoder.encode("123456");
        System.out.println(result3);
        //$2a$10$/nMjQ1V7jSdlhG2Hz4fuYOl.bYK9BmybdJSA6W/TbSw7eXACa4MrG
        //$2a$10$vAQADdW9tPe5yOD6/u6.kuBtbR0WbS4pgUw20wZs3Oxzt3t7BFws6
        //$2a$10$WOaEEDJMJiuRYaDbp9d8A.KbHcDWBJCsz3m1BfLNp2TR7HqokvJLG
    }

    @Test
    public void testPasswordEncoder2(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean flag1 = passwordEncoder.matches("123456", "$2a$10$/nMjQ1V7jSdlhG2Hz4fuYOl.bYK9BmybdJSA6W/TbSw7eXACa4MrG");
        System.out.println(flag1);
        boolean flag2 = passwordEncoder.matches("123456", "$2a$10$vAQADdW9tPe5yOD6/u6.kuBtbR0WbS4pgUw20wZs3Oxzt3t7BFws6");
        System.out.println(flag2);
        boolean flag3 = passwordEncoder.matches("123456", "$2a$10$WOaEEDJMJiuRYaDbp9d8A.KbHcDWBJCsz3m1BfLNp2TR7HqokvJLG");
        System.out.println(flag3);
    }
}