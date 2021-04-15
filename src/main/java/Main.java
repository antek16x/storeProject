import finances.Price;
import impl.ProductRepositoryImpl;
import impl.Store;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Store store = new Store(new ProductRepositoryImpl());
        ApplicationContext context
                = new ClassPathXmlApplicationContext("config.xml");
        Store store = context.getBean(Store.class);

        store.productDelivery("zeszyt", new Price(2, 38), 30);
        store.productDelivery("zeszyt", new Price(2, 38), 30);
        store.productDelivery("olowek", new Price(1, 50), 13);
        int nr = 0;
        Scanner sc = new Scanner(System.in);

        while (nr != 4) {
            hello();
            menu();
            try {
                nr = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException nfe) {
                nr = 5;
            }
            switch (nr) {
                case 1:
                    System.out.println(store.toString());
                    break;
                case 2:
                    sale(store);
                    break;
                case 3:
                    delivery(store);
                    break;
                case 4:
                    goodbye();
                    break;
                default:
                    System.out.println("Nie ma takiego wyboru, podaj jeszcze raz...");
            }
        }
    }

    private static void delivery(Store store) {
        String name;
        int i;
        int zl, gr;
        String price;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Podaj nazwę: ");
            name = sc.nextLine();
            System.out.print("Podaj ilosc: ");
            i = Integer.parseInt(sc.nextLine());
            System.out.print("Podaj cene w formacie zl,gr: ");
            price = sc.nextLine();
            zl = Integer.parseInt(price.substring(0, price.indexOf(",")));
            gr = Integer.parseInt(price.substring(price.indexOf(",") + 1));
            store.productDelivery(name, new Price(zl, gr), i);
        } catch (Exception iae) {
            System.out.println("Błąd danych, operacja się nie powiodła");
        }
    }

    private static void sale(Store store) {
        String name;
        int i;
        int zl, gr;
        String price;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Podaj nazwę produktu do kupienia: ");
            name = sc.nextLine();
            System.out.print("Podaj ilosc: ");
            i = Integer.parseInt(sc.nextLine());
            int nr = store.productAmount(name);
            if (nr == 0) {
                System.out.println("Brak produktu w sklepie");
            } else if (i <= nr) {
                System.out.println("Do zaplaty: " + store.priceToPay(name, i));
                store.productSale(name, i);
            } else {
                System.out.println("Za mało produktu w sklepie");
            }
        } catch (IllegalArgumentException iae) {
            System.out.println("Błąd, operacja się nie powiodła");
        }
    }

    private static void menu() {
        String menu = " 1. Wyswietl produkty\n 2. Kup produkt\n 3. Dostawa produktu do sklepu\n 4. Koniec ";
        System.out.println("Wybierz opcję:");
        System.out.println(menu);
    }

    private static void hello() {
        System.out.println("=============================");
        System.out.println(" Aplikacja obsługująca sklep");
        System.out.println("=============================");
    }

    private static void goodbye() {
        System.out.println("=============================");
        System.out.println("        KONIEC");
        System.out.println("=============================");
    }
}
