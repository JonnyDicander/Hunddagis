
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Scanner används för att läsa användarens inmatning
        Scanner scanner = new Scanner(System.in);

        // Skapar hundregistret
        DogRegister register = new DogRegister();

        System.out.println("\nVälkommen till hunddagiset!");

        int choice = -1;

        // Huvudmenyn körs tills användaren väljer 0
        while (choice != 0) {

            System.out.println("\n===== HUNDDAGIS =====");
            System.out.println("1. Registrera hund");
            System.out.println("2. Visa alla hundar");
            System.out.println("3. Sök efter hund");
            System.out.println("4. Ändra hunduppgifter");
            System.out.println("5. Ta bort hund");
            System.out.println("6. Checka in hund");
            System.out.println("7. Checka ut hund");
            System.out.println("8. Visa incheckade hundar");
            System.out.println("9. Visa statistik");
            System.out.println("0. Avsluta");
            System.out.println("=====================");

            choice = readMenuChoice(scanner);

            // 1. REGISTRERA HUND
            if (choice == 1) {

                System.out.println("\n--- REGISTRERA HUND ---");

                String name = readDogText(
                        scanner, "Ange namn: ");

                String breed = readDogText(
                        scanner, "Ange ras: ");

                // Kontrollerar att chipnumret är unikt
                String chipNumber;

                while (true) {

                    chipNumber = readChipNumber(
                            scanner, "Ange chipnummer: ");

                    // Finns chipnumret inte går vi vidare
                    if (register.findDog(chipNumber) == null) {
                        break;
                    }

                    System.out.println(
                            "Detta chipnummer finns redan registrerat! Vänligen försök igen.");
                }

                String gender = readGender(
                        scanner, "Ange kön (H = Hane / T = Tik): ");

                int age = readAge(
                        scanner, "Ange ålder (0-25): ");

                boolean canSitWithOthers = readYesNo(
                        scanner, "Kan sitta med andra hundar? (J/N): ");

                boolean neutered = readYesNo(
                        scanner, "Kastrerad? (J/N): ");

                boolean needsGrooming = readYesNo(
                        scanner, "Behöver pälsvård? (J/N): ");

                boolean visionImpaired = readYesNo(
                        scanner, "Synnedsättning? (J/N): ");

                boolean hearingImpaired = readYesNo(
                        scanner, "Hörselnedsättning? (J/N): ");

                // Skapar hundobjektet
                Dog dog = new Dog(
                        name, breed, chipNumber, gender, age,
                        canSitWithOthers, neutered, needsGrooming,
                        visionImpaired, hearingImpaired);

                // Registrerar hunden
                register.addDog(dog);

                // 2. VISA ALLA HUNDAR
            } else if (choice == 2) {

                register.showAllDogs();

                // 3. SÖK EFTER HUND
            } else if (choice == 3) {

                System.out.println("\n--- SÖK EFTER HUND ---");

                String search = readNonEmpty(
                        scanner, "Ange namn eller chipnummer: ");

                Dog dog = register.searchDog(search);

                if (dog != null) {

                    System.out.println("\nHunden hittades!");
                    dog.showInfo();

                } else {

                    System.out.println("Ingen hund hittades!");
                }

                // 4. ÄNDRA HUNDUPPGIFTER
            } else if (choice == 4) {

                System.out.println("\n--- ÄNDRA HUNDUPPGIFTER ---");

                String chipNumber = readChipNumber(
                        scanner, "Ange hundens chipnummer: ");

                Dog dog = register.findDog(chipNumber);

                if (dog == null) {

                    System.out.println("Hunden hittades inte.");

                } else {

                    String name = readDogText(
                            scanner, "Nytt namn: ");

                    String breed = readDogText(
                            scanner, "Ny ras: ");

                    int age = readAge(
                            scanner, "Ny ålder (0-25): ");

                    String gender = readGender(
                            scanner, "Nytt kön (H = Hane / T = Tik): ");

                    boolean canSitWithOthers = readYesNo(
                            scanner, "Kan vara med andra hundar? (J/N): ");

                    boolean neutered = readYesNo(
                            scanner, "Kastrerad? (J/N): ");

                    boolean needsGrooming = readYesNo(
                            scanner, "Behöver pälsvård? (J/N): ");

                    boolean visionImpaired = readYesNo(
                            scanner, "Nedsatt syn? (J/N): ");

                    boolean hearingImpaired = readYesNo(
                            scanner, "Nedsatt hörsel? (J/N): ");

                    // Uppdaterar hundens uppgifter
                    dog.updateInfo(
                            name, breed, gender, age,
                            canSitWithOthers, neutered, needsGrooming,
                            visionImpaired, hearingImpaired);

                    System.out.println(
                            "Hundens uppgifter har uppdaterats!");
                }

                // 5. TA BORT HUND
            } else if (choice == 5) {

                System.out.println("\n--- TA BORT HUND ---");

                String chipNumber = readChipNumber(
                        scanner, "Ange hundens chipnummer: ");

                Dog dog = register.findDog(chipNumber);

                if (dog == null) {

                    System.out.println("Hunden hittades inte.");

                } else {

                    System.out.println("Du har valt att ta bort:");
                    dog.showInfo();

                    boolean confirm = readYesNo(
                            scanner, "Är du säker? (J/N): ");

                    if (confirm) {

                        register.removeDog(chipNumber);

                    } else {

                        System.out.println("Borttagningen avbröts.");
                    }
                }

                // 6. CHECKA IN HUND
            } else if (choice == 6) {

                System.out.println("\n--- CHECKA IN HUND ---");

                String chipNumber = readChipNumber(
                        scanner, "Ange hundens chipnummer: ");

                Dog dog = register.findDog(chipNumber);

                if (dog == null) {

                    System.out.println("Hunden hittades inte!");

                } else if (dog.isCheckedIn()) {

                    System.out.println("Hunden är redan incheckad!");

                } else {

                    dog.setCheckedIn(true);

                    System.out.println("Hunden " + dog.getName()
                            + " har checkats in!");
                }

                // 7. CHECKA UT HUND
            } else if (choice == 7) {

                System.out.println("\n--- CHECKA UT HUND ---");

                String chipNumber = readChipNumber(
                        scanner, "Ange hundens chipnummer: ");

                Dog dog = register.findDog(chipNumber);

                if (dog == null) {

                    System.out.println("Hunden hittades inte!");

                } else if (!dog.isCheckedIn()) {

                    System.out.println("Hunden är redan utcheckad!");

                } else {

                    dog.setCheckedIn(false);

                    System.out.println("Hunden " + dog.getName()
                            + " har checkats ut!");
                }

                // 8. VISA INCHECKADE HUNDAR
            } else if (choice == 8) {

                register.showCheckedInDogs();

                // 9. VISA STATISTIK
            } else if (choice == 9) {

                register.showStatistics();


                // 0. AVSLUTA
            } else if (choice == 0) {

                System.out.println(
                        "\nTack för att du besökte hunddagiset!");
                System.out.println("Välkommen åter!");
            }
        }

        scanner.close();
    }

    // ==========================================
    // HJÄLPMETOD 1 - KONTROLL AV MENYVAL
    // ==========================================

    private static int readMenuChoice(Scanner scanner) {

        while (true) {

            System.out.print("Ditt val: ");
            String input = scanner.nextLine().trim();

            try {

                int choice = Integer.parseInt(input);

                if (choice == 0 || (choice >= 1 && choice <= 9)) {
                    return choice;
                }

                System.out.println(
                        "Välj gärna ett tal mellan 1 och 9, eller 0 för att avsluta.");
            } catch (NumberFormatException e) {

                System.out.println(
                        "Hoppsan! Skriv gärna ett heltal mellan 1 och 9, eller 0 för att avsluta.");
            }
        }
    }

    // ==========================================
    // HJÄLPMETOD 2 - KONTROLL AV TEXT
    // ==========================================

    private static String readNonEmpty(
            Scanner scanner, String prompt) {

        while (true) {

            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Du glömde att fylla i fältet. Försök gärna igen!");
        }
    }

    // ==========================================
    // HJÄLPMETOD 3 - NAMN OCH RAS
    // ==========================================

    private static String readDogText(
            Scanner scanner, String prompt) {

        while (true) {

            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Tillåter bokstäver, mellanslag,
            // bindestreck och apostrof
            if (input.matches("[\\p{L}]+(?:[ '-][\\p{L}]+)*")) {
                return input;
            }

            System.out.println(
                    "Ange endast bokstäver. Vänligen försök igen.");
        }
    }

    // ==========================================
    // HJÄLPMETOD 4 - CHIPNUMMER
    // ==========================================

    private static String readChipNumber(
            Scanner scanner, String prompt) {

        while (true) {

            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            // Godkänner endast siffror
            if (input.matches("[0-9]+")) {
                return input;
            }

            System.out.println(
                    "Detta är inte ett giltigt chipnummer. Försök igen.");
        }
    }

    // ==========================================
    // HJÄLPMETOD 5 - KÖN
    // ==========================================

    private static String readGender(
            Scanner scanner, String prompt) {

        while (true) {

            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("H")) {
                return "H";
            }

            if (input.equalsIgnoreCase("T")) {
                return "T";
            }

            System.out.println(
                    "Ange H för hane eller T för tik. Vänligen försök igen.");
        }
    }

    // ==========================================
    // HJÄLPMETOD 6 - ÅLDER 0-25
    // ==========================================

    private static int readAge(
            Scanner scanner, String prompt) {

        while (true) {

            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {

                int age = Integer.parseInt(input);

                if (age >= 0 && age <= 25) {
                    return age;
                }

                System.out.println(
                        "Åldern måste vara mellan 0 och 25 år.");

            } catch (NumberFormatException e) {

                System.out.println(
                        "Det verkar inte vara ett heltal. Försök gärna igen!");
            }
        }
    }

    // ==========================================
    // HJÄLPMETOD 7 - JA ELLER NEJ
    // ==========================================

    private static boolean readYesNo(
            Scanner scanner, String prompt) {

        while (true) {

            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("J")) {
                return true;
            }

            if (input.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println(
                    "Skriv gärna J för ja eller N för nej.");
        }
    }
}
