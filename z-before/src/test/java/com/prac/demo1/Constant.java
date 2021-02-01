package com.prac.demo1;

/**
 * @Auther: liweizhi
 * @Date: 2019/2/26 14:11
 * @Description:
 */
public class Constant {
    public static void main(String[] args) {
        Integer a = null;

    }

    enum AppServiceType {
        PHP("p"),
        JAVA("j"),
        DOTNET("d"),
        PYTHON("py"),
        NODEJS("nj"),
        GO("go"),
        C("c")
        ;

        private String value;
        AppServiceType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}
