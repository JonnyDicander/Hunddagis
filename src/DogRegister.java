

import java.util.ArrayList;

public class DogRegister {

    // Lista som lagrar alla registrerade hundar
    private ArrayList<Dog> dogs = new ArrayList<>();

    // ==========================================
    // REGISTRERA EN NY HUND
    // ==========================================

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

    // ==========================================
    // VISA ALLA REGISTRERADE HUNDAR
    // ==========================================

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

    // ==========================================
    // SÖK EFTER HUND MED CHIPNUMMER
    // ==========================================

    public Dog findDog(String chipNumber) {

        for (Dog dog : dogs) {

            if (dog.getChipNumber().equals(chipNumber)) {
                return dog;
            }
        }

        return null;
    }

    // ==========================================
    // SÖK EFTER HUND MED NAMN ELLER CHIPNUMMER
    // ==========================================

    public Dog searchDog(String search) {

        for (Dog dog : dogs) {

            if (dog.getName().equalsIgnoreCase(search) ||
                    dog.getChipNumber().equals(search)) {

                return dog;
            }
        }

        return null;
    }

    // ==========================================
    // TA BORT EN REGISTRERAD HUND
    // ==========================================

    public boolean removeDog(String chipNumber) {

        Dog dog = findDog(chipNumber);

        if (dog != null) {

            dogs.remove(dog);

            System.out.println("Hunden " + dog.getName() +
                    " har tagits bort från registret!");

            return true;
        }

        System.out.println(
                "Ingen hund med detta chipnummer hittades i hunddagisets register!");

        return false;
    }

    // ==========================================
    // VISA ALLA INCHECKADE HUNDAR
    // ==========================================

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

    // ==========================================
    // VISA STATISTIK ÖVER HUNDDAGISET
    // ==========================================

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

    // ==========================================
    // VISA KOSTNADER FÖR INCHECKADE HUNDAR
    // ==========================================

    public void showCosts() {

        System.out.println("\n===== HUNDDAGISETS KOSTNADER =====");

        double totalCost = 0;
        int count = 0;

        // Går igenom alla registrerade hundar
        for (Dog dog : dogs) {

            // Räknar bara hundar som är incheckade
            if (dog.isCheckedIn()) {

                // Hämtar antal minuter från Dog.java
                long minutes = dog.getStayMinutes();

                // Omvandlar minuter till timmar och minuter
                long hours = minutes / 60;
                long remainingMinutes = minutes % 60;

                // Hämtar kostnaden från Dog.java
                double cost = dog.getCost();

                System.out.println("\nHund: " + dog.getName());
                System.out.println("Chipnummer: " + dog.getChipNumber());

                System.out.println("Vistelsetid: " + hours +
                        " timmar och " + remainingMinutes + " minuter");

                System.out.printf("Kostnad: %.2f kr%n", cost);

                // Lägger till kostnaden i totalsumman
                totalCost += cost;
                count++;
            }
        }

        if (count == 0) {

            System.out.println("Inga hundar är incheckade just nu.");

        } else {

            System.out.println("\n--- SAMMANSTÄLLNING ---");

            System.out.println("Antal incheckade hundar: " + count);

            System.out.printf("Total kostnad: %.2f kr%n", totalCost);
        }

        System.out.println("=================================");
    }
}
