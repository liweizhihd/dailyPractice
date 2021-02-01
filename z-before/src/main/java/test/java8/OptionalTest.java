package test.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * @auther: liweizhi
 * Date: 2019/3/27 15:47
 * Description:
 */
public class OptionalTest {

    @Test
    public void test002(){
        Champion champion = new Champion("");
        System.out.println(champion);
        champion.setName("");

    }

    public void test001() {
        Competition comp = new Competition(new Result(new Champion("dahuang")));
        System.out.println(getChampionName(comp));
        Competition comp2 = new Competition(new Result(new Champion()));
        System.out.println(getChampionName(comp2));
    }

    public String getChampionName(Competition comp) throws IllegalArgumentException {
        return Optional.ofNullable(comp)
                .map(c -> c.getResult())
                .map(r -> r.getChampion())
                .map(u -> u.getName())
                .orElseThrow(() -> new IllegalArgumentException("The value of param comp isn't available."));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Competition {
        @NonNull
        private Result result;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Result {
        private Champion champion;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Champion {
        private String name;

        public void setName(String name) throws IllegalArgumentException {
            this.name = Optional.ofNullable(name)
                    .filter(isNameValid)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid username."));
        }

        final Predicate<String> isNameValid = StringUtils::isNotBlank;
        final Predicate<String> isNameValid2 = (name) -> StringUtils.isNotBlank(name);
    }
}
