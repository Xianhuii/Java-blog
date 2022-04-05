package proxy;

import java.util.LinkedList;
import java.util.List;

/**
 * 代理设计模式 开发软件
 */
public class ProxyDesignModel {
    interface Programmable {
        void developSoftware();
    }

    static class JavaProgrammer implements Programmable {
        @Override
        public void developSoftware() {
            System.out.println("编写Java代码");
        }
    }

    static class JavaScriptProgrammer implements Programmable {
        @Override
        public void developSoftware() {
            System.out.println("编写JavaScript代码");
        }
    }

    static class ITCompany implements Programmable {
        private List<Programmable> programmers = new LinkedList<>();

        public void recruitProgrammer(Programmable programmer) {
            programmers.add(programmer);
        }

        @Override
        public void developSoftware() {
            designProduct();
            programmers.forEach(Programmable::developSoftware);
            operate();
        }

        public void designProduct() {
            System.out.println("产品设计");
        }

        public void operate() {
            System.out.println("上线运营");
        }
    }

    public static void main(String[] args) {
        ITCompany company = new ITCompany();
        company.recruitProgrammer(new JavaProgrammer());
        company.recruitProgrammer(new JavaScriptProgrammer());
        company.developSoftware();
    }
}
