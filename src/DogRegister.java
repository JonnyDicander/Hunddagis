
import java.util.ArrayList;

public class DogRegister {

    // Lista som lagrar alla registrerade hundar
    private ArrayList<Dog> dogs = new ArrayList<>();

    // Registrera en ny hund
    public boolean addDog(Dog dog) {

        // Kontrollera om chipnumret redan finns
        if (findDog(dog.getChipNumber()) != null) {
            System.out.println("Chipnumret finns redan i hunddagisets register!");
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
            System.out.println("Inga hundar är registrerade!");
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

    // Sök efter hund med namn eller angivet chipnummer
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
                    " har tagits bort från registret!");

            return true;
        }

        System.out.println("Ingen hund med detta chipnummer hittades i hunddagisets register!");
        return false;
    }

    // Visar alla hundar som är incheckade i hunddagiset
    public void showCheckedInDogs() {

        System.out.println("\n--- INCHECKADE HUNDAR ---");

        int count = 0;

        for (Dog dog : dogs) {

            if (dog.isCheckedIn()) {

                dog.showInfo();
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Inga hundar är incheckade just nu.");
        } else {
            System.out.println("Totalt incheckade hundar: " + count);
        }
    }
}
