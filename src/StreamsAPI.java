import java.util.ArrayList;
import java.util.List;

public class StreamsAPI {

    public static void concat(String fruta){

        System.out.println(fruta + " é uma fruta");
    }

    public static void main(String[] args) {

        List<String> frutas = new ArrayList<>();

        frutas.add("maçã");
        frutas.add("banana");

        frutas.stream().forEach(fruta -> System.out.println(fruta));

        // Usando referência de método para imprimir cada fruta
        frutas.stream().forEach(System.out::println);
        frutas.stream().forEach(StreamsAPI::concat);

        List<FrutaObject> frutaObjects;

        // Usando como paramêtro de um construtor
        frutaObjects = frutas.stream().map(FrutaObject::new).toList();
        frutaObjects.stream().forEach(System.out::println);
    }

    public static class FrutaObject{

        private String name;

        public FrutaObject(String name){

            this.name = name;
        }

        @Override
        public String toString() {
            return "FrutaObject{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
