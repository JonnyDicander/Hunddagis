
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

    // Visar statistik över hunddagiset
    public void showStatistics() {

        int totalDogs = dogs.size();
        int checkedInDogs = 0;

        // Räknare för hundarnas särskilda behov
        int groomingDogs = 0;
        int visionImpairedDogs = 0;
        int hearingImpairedDogs = 0;

        // Går igenom alla registrerade hundar
        for (Dog dog : dogs) {

            if (dog.isCheckedIn()) {
                checkedInDogs++;
            }

            if (dog.isNeedsGrooming()) {
                groomingDogs++;
            }

            if (dog.isVisionImpaired()) {
                visionImpairedDogs++;
            }

            if (dog.isHearingImpaired()) {
                hearingImpairedDogs++;
            }
        }

        int notCheckedInDogs = totalDogs - checkedInDogs;

        // Skriver ut statistiken
        System.out.println("\n===== HUNDDAGISETS STATISTIK =====");

        System.out.println("\n--- REGISTRERADE HUNDAR ---");
        System.out.println("Totalt registrerade hundar: " + totalDogs);
        System.out.println("Incheckade hundar: " + checkedInDogs);
        System.out.println("Ej incheckade hundar: " + notCheckedInDogs);

        System.out.println("\n--- SÄRSKILDA BEHOV ---");
        System.out.println("Behöver pälsvård: " + groomingDogs);
        System.out.println("Har synnedsättning: " + visionImpairedDogs);
        System.out.println("Har hörselnedsättning: " + hearingImpairedDogs);

        System.out.println("=================================");
    }
}

