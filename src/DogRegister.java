
import java.util.ArrayList;

public class DogRegister {

    // Lista som lagrar alla registrerade hundar
    private ArrayList<Dog> dogs = new ArrayList<>();

    // Registrera en ny hund
    public boolean addDog(Dog dog) {

        // Kontrollera om chipnumret redan finns
        if (findDog(dog.getChipNumber()) != null) {
            System.out.println("Chipnumret finns redan i registret!");
            return false;
        }

        dogs.add(dog);
        System.out.println("Hunden " + dog.getName() +
                " har registrerats!");

        return true;
    }

    // Visa alla registrerade hundar
    public void showAllDogs() {

        if (dogs.isEmpty()) {
            System.out.println("Inga hundar registrerade!");
        } else {

            System.out.println("\n--- REGISTRERADE HUNDAR ---");

            for (Dog dog : dogs) {
                dog.showInfo();
            }
        }
    }

    // Sök efter hund med chipnummer
    public Dog findDog(String chipNumber) {

        for (Dog dog : dogs) {

            if (dog.getChipNumber().equals(chipNumber)) {
                return dog;
            }
        }

        return null;
    }

    // Sök efter hund med namn eller chipnummer
    public Dog searchDog(String search) {

        for (Dog dog : dogs) {

            if (dog.getName().equalsIgnoreCase(search) ||
                    dog.getChipNumber().equals(search)) {

                return dog;
            }
        }

        return null;
    }

    // Ta bort en registrerad hund
    public boolean removeDog(String chipNumber) {

        Dog dog = findDog(chipNumber);

        if (dog != null) {
            dogs.remove(dog);

            System.out.println("Hunden " + dog.getName() +
                    " har tagits bort!");

            return true;
        }

        System.out.println("Ingen hund med detta chipnummer hittades!");
        return false;
    }
}
