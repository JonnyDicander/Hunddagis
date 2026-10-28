
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        // Scanner används för användarens inmatning
        Scanner scanner = new Scanner(System.in);

        // Bestämmer hur datum och tid ska visas
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
            System.out.println("10. Visa kostnader");
            System.out.println("0. Avsluta");
            System.out.println("=====================");

            choice = readMenuChoice(scanner);

            // ==========================================
            // 1. REGISTRERA HUND
            // ==========================================

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

                    if (register.findDog(chipNumber) == null) {
                        break;
                    }

                    System.out.println(
                            "Detta chipnummer finns redan registrerat! Försök igen.");
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

                // ==========================================
                // 2. VISA ALLA HUNDAR
                // ==========================================

            } else if (choice == 2) {

                register.showAllDogs();

                // ==========================================
                // 3. SÖK EFTER HUND
                // ==========================================

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

                // ==========================================
                // 4. ÄNDRA HUNDUPPGIFTER
                // ==========================================

            } else if (choice == 4) {

                System.out.println("\n--- ÄNDRA HUNDUPPGIFTER ---");

                String chipNumber = readChipNumber(
                        scanner, "Ange hundens chipnummer: ");

                Dog dog = register.findDog(chipNumber);

                if (dog == null) {

                    System.out.println("Hunden hittades inte!");

                } else {

                    System.out.println("\nHunden " + dog.getName() + " hittades!");

                    // Visar hundens nuvarande uppgifter
                    dog.showInfo();

                    int editChoice = -1;

                    // Ändringsmenyn körs tills användaren väljer 0
                    while (editChoice != 0) {

                        System.out.println("\n--- ÄNDRA HUNDUPPGIFTER ---");
                        System.out.println("1. Ändra namn");
                        System.out.println("2. Ändra ras");
                        System.out.println("3. Ändra ålder");
                        System.out.println("4. Ändra kön");
                        System.out.println("5. Ändra samsittning");
                        System.out.println("6. Ändra kastrering");
                        System.out.println("7. Ändra pälsvård");
                        System.out.println("8. Ändra synnedsättning");
                        System.out.println("9. Ändra hörselnedsättning");
                        System.out.println("0. Tillbaka till huvudmenyn");
                        System.out.println("==========================");

                        editChoice = readMenuChoice(scanner, 9);

                        // Ändra namn
                        if (editChoice == 1) {

                            String newName = readDogText(
                                    scanner, "Ange nytt namn: ");

                            dog.setName(newName);

                            System.out.println(
                                    "Hundens namn har ändrats till "
                                            + dog.getName() + "!");

                            // Ändra ras
                        } else if (editChoice == 2) {

                            String newBreed = readDogText(
                                    scanner, "Ange ny ras: ");

                            dog.setBreed(newBreed);

                            System.out.println(
                                    "Hundens ras har uppdaterats!");

                            // Ändra ålder
                        } else if (editChoice == 3) {

                            int newAge = readAge(
                                    scanner, "Ange ny ålder (0-25): ");

                            dog.setAge(newAge);

                            System.out.println(
                                    "Hundens ålder har uppdaterats!");

                            // Ändra kön
                        } else if (editChoice == 4) {

                            String newGender = readGender(
                                    scanner, "Ange nytt kön (H/T): ");

                            dog.setGender(newGender);

                            System.out.println(
                                    "Hundens kön har uppdaterats!");

                            // Ändra samsittning
                        } else if (editChoice == 5) {

                            boolean value = readYesNo(
                                    scanner, "Kan sitta med andra hundar? (J/N): ");

                            dog.setCanSitWithOthers(value);

                            System.out.println(
                                    "Hundens samsittning har uppdaterats!");

                            // Ändra kastrering
                        } else if (editChoice == 6) {

                            boolean value = readYesNo(
                                    scanner, "Kastrerad? (J/N): ");

                            dog.setNeutered(value);

                            System.out.println(
                                    "Hundens kastreringsstatus har uppdaterats!");

                            // Ändra pälsvård
                        } else if (editChoice == 7) {

                            boolean value = readYesNo(
                                    scanner, "Behöver pälsvård? (J/N): ");

                            dog.setNeedsGrooming(value);

                            System.out.println(
                                    "Hundens pälsvårdsbehov har uppdaterats!");

                            // Ändra synnedsättning
                        } else if (editChoice == 8) {

                            boolean value = readYesNo(
                                    scanner, "Synnedsättning? (J/N): ");

                            dog.setVisionImpaired(value);

                            System.out.println(
                                    "Hundens synuppgifter har uppdaterats!");

                            // Ändra hörselnedsättning
                        } else if (editChoice == 9) {

                            boolean value = readYesNo(
                                    scanner, "Hörselnedsättning? (J/N): ");

                            dog.setHearingImpaired(value);

                            System.out.println(
                                    "Hundens hörseluppgifter har uppdaterats!");
                        }

                        // Visar uppdaterade uppgifter
                        if (editChoice >= 1 && editChoice <= 9) {
                            dog.showInfo();
                        }
                    }
                }

                // ==========================================
                // 5. TA BORT HUND
                // ==========================================

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

                // ==========================================
                // 6. CHECKA IN HUND
                // ==========================================

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

                    System.out.println("Datum och tid: "
                            + dog.getCheckInTime().format(formatter));

                    System.out.println("Timpris: 50 kr");
                }

                // ==========================================
                // 7. CHECKA UT HUND
                // ==========================================

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

                    // Checkar ut hunden och sparar tiden
                    dog.setCheckedIn(false);

                    // Hämtar vistelsetiden
                    long minutes = dog.getStayMinutes();

                    long hours = minutes / 60;
                    long remainingMinutes = minutes % 60;

                    System.out.println("\nHunden " + dog.getName()
                            + " har checkats ut!");

                    System.out.println("Incheckning: "
                            + dog.getCheckInTime().format(formatter));

                    System.out.println("Utcheckning: "
                            + dog.getCheckOutTime().format(formatter));

                    System.out.println("Vistelsetid: " + hours
                            + " timmar och " + remainingMinutes
                            + " minuter");

                    // Visar slutkostnaden
                    System.out.printf("Slutkostnad: %.2f kr%n",
                            dog.getCost());
                }

                // ==========================================
                // 8. VISA INCHECKADE HUNDAR
                // ==========================================

            } else if (choice == 8) {

                register.showCheckedInDogs();

                // ==========================================
                // 9. VISA STATISTIK
                // ==========================================

            } else if (choice == 9) {

                register.showStatistics();

                // ==========================================
                // 10. VISA KOSTNADER
                // ==========================================

            } else if (choice == 10) {

                register.showCosts();

                // ==========================================
                // 0. AVSLUTA
                // ==========================================

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

    // Huvudmenyn accepterar 0-10
    private static int readMenuChoice(Scanner scanner) {
        return readMenuChoice(scanner, 10);
    }

    // Kan användas för menyer med olika maxvärden
    private static int readMenuChoice(
            Scanner scanner, int maxChoice) {

        while (true) {

            System.out.print("Ditt val: ");
            String input = scanner.nextLine().trim();

            try {

                int choice = Integer.parseInt(input);

                if (choice >= 0 && choice <= maxChoice) {
                    return choice;
                }

                System.out.println(
                        "Välj ett tal mellan 0 och " + maxChoice + ".");

            } catch (NumberFormatException e) {

                System.out.println(
                        "Hoppsan! Skriv ett heltal mellan 0 och "
                                + maxChoice + ".");
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
                    "Du glömde att fylla i fältet. Försök igen!");
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
                    "Ange endast bokstäver. Försök igen.");
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
                    "Ange H för hane eller T för tik.");
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
                        "Det verkar inte vara ett heltal. Försök igen!");
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
                    "Skriv J för ja eller N för nej.");
        }
    }
}
