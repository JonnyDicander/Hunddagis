
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Scanner används för att läsa det som användaren skriver in.
        Scanner scanner = new Scanner(System.in);

        // Skapar hundregistret som hanterar hundarna.
        DogRegister register = new DogRegister();

        // Ett vänligt välkomstmeddelande som visas ovanför menynvalen när programmet körs.
        System.out.println("\nVälkommen till hunddagiset!");

        // Startvärdet är -1 eftersom användaren ännu inte valt något.
        int choice = -1;

        // Huvudloopen fortsätter så länge användaren inte väljer 8.
        while (choice != 8) {

            // Skriver ut huvudmenyn.
            System.out.println("\n===== HUNDDAGIS =====");
            System.out.println("1. Registrera hund");
            System.out.println("2. Visa alla hundar");
            System.out.println("3. Sök efter hund");
            System.out.println("4. Ändra hunduppgifter");
            System.out.println("5. Ta bort hund");
            System.out.println("6. Checka in hund");
            System.out.println("7. Checka ut hund");
            System.out.println("8. Avsluta");
            System.out.println("=====================");

            // Läser in ett giltigt menyval mellan 1 och 8.
            // Om användaren skriver fel frågar metoden igen, tills påståendet blir sant.
            choice = readMenuChoice(scanner);

            // 1. Registrera hund
            if (choice == 1) {

                System.out.println("\n--- REGISTRERA HUND ---");

                // Läser in text och kontrollerar att den inte är tom.
                String name = readNonEmpty(scanner, "Ange namn: ");
                String breed = readNonEmpty(scanner, "Ange ras: ");
                String chipNumber = readNonEmpty(
                        scanner, "Ange chipnummer: ");
                String gender = readNonEmpty(scanner, "Ange kön: ");

                // Åldern måste vara ett heltal som är minst 0.
                int age = readInt(scanner, "Ange ålder: ", 0);

                // Läs in svar på frågor som bara tillåter J eller N.
                boolean canSitWithOthers = readYesNo(
                        scanner, "Kan sitta med andra hundar? (J/N): ");

                boolean neutered = readYesNo(
                        scanner, "Kastrerad? (J/N): ");

                boolean needsGrooming = readYesNo(
                        scanner, "Behöver pälsvård? (J/N): ");

                String coatType = readNonEmpty(
                        scanner, "Långhårig eller korthårig?: ");

                boolean visionImpaired = readYesNo(
                        scanner, "Synnedsättning? (J/N): ");

                boolean hearingImpaired = readYesNo(
                        scanner, "Hörselnedsättning? (J/N): ");

                // Skapar ett hundobjekt med de inmatade uppgifterna.
                Dog dog = new Dog(
                        name, breed, chipNumber, gender, age,
                        canSitWithOthers, neutered, needsGrooming,
                        coatType, visionImpaired, hearingImpaired );

                // Lägger till hunden i registret.
                register.addDog(dog);

                // 2. Visa alla hundar
            } else if (choice == 2) {

                // Ber registret att visa alla registrerade hundar.
                register.showAllDogs();

                // 3. Sök efter hund
            } else if (choice == 3) {

                System.out.println("\n--- SÖK EFTER HUND ---");

                // Användaren måste skriva något att söka efter.
                String search = readNonEmpty(
                        scanner, "Ange namn eller chipnummer: ");

                // Söker efter hunden i registret.
                Dog dog = register.searchDog(search);

                // Om hunden hittas skrivs dess uppgifter ut.
                if (dog != null) {
                    System.out.println("\nHunden hittades!");
                    dog.showInfo();

                    // null betyder här att ingen hund hittades.
                } else {
                    System.out.println("Ingen hund hittades!");
                }

                // 4. Ändra hunduppgifter
            } else if (choice == 4) {

                System.out.println("\n--- ÄNDRA HUNDUPPGIFTER ---");

                // Chipnumret används för att hitta rätt hund.
                String chipNumber = readNonEmpty(
                        scanner, "Ange hundens chipnummer: ");

                Dog dog = register.findDog(chipNumber);

                // Om hunden inte hittas kan uppgifterna inte ändras.
                if (dog == null) {

                    System.out.println("Hunden hittades inte.");

                } else {

                    // Läser in hundens nya uppgifter.
                    String name = readNonEmpty(scanner, "Nytt namn: ");
                    String breed = readNonEmpty(scanner, "Ny ras: ");
                    int age = readInt(scanner, "Ny ålder: ", 0);
                    String gender = readNonEmpty(scanner, "Nytt kön: ");
                    String coatType = readNonEmpty(
                            scanner, "Ny pälstyp: ");

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

                    // Skickar de nya uppgifterna till hundobjektet.
                    dog.updateInfo(
                            name, breed, gender, age,
                            canSitWithOthers, neutered, needsGrooming,
                            coatType, visionImpaired, hearingImpaired );

                    System.out.println(
                            "Hundens uppgifter har uppdaterats!");
                }

                // 5. Ta bort hunden ur registret
            } else if (choice == 5) {

                // Frågar efter chipnumret för hunden som ska tas bort.
                String chipNumber = readNonEmpty(
                        scanner, "Ange hundens chipnummer: ");

                Dog dog = register.findDog(chipNumber);

                if (dog == null) {

                    System.out.println("Hunden hittades inte.");

                } else {

                    // Visar hundens uppgifter innan borttagning.
                    System.out.println("Du har valt att ta bort:");
                    dog.showInfo();

                    // Användaren måste bekräfta borttagningen.
                    boolean confirm = readYesNo(
                            scanner, "Är du säker? (J/N): ");

                    if (confirm) {

                        register.removeDog(chipNumber);

                    } else {

                        System.out.println("Borttagningen avbröts.");
                    }
                }

                // 6. Checka in en hund
            } else if (choice == 6) {

                System.out.println("\n--- CHECKA IN HUND ---");

                String chipNumber = readNonEmpty(
                        scanner, "Ange hundens chipnummer: ");

                Dog dog = register.findDog(chipNumber);

                if (dog == null) {

                    System.out.println("Hunden hittades inte!");

                    // Kontrollerar att hunden inte redan är incheckad.
                } else if (dog.isCheckedIn()) {

                    System.out.println("Hunden är redan incheckad!");

                } else {

                    // Ändrar hundens status till incheckad.
                    dog.setCheckedIn(true);

                    System.out.println("Hunden " + dog.getName()
                            + " har checkats in!");
                }

                // 7. Checka ut en hund
            } else if (choice == 7) {

                System.out.println("\n--- CHECKA UT HUND ---");

                String chipNumber = readNonEmpty(
                        scanner, "Ange hundens chipnummer: ");

                Dog dog = register.findDog(chipNumber);

                if (dog == null) {

                    System.out.println("Hunden hittades inte!");

                    // Kontrollerar att hunden faktiskt är incheckad.
                } else if (!dog.isCheckedIn()) {

                    System.out.println("Hunden är redan utcheckad!");

                } else {

                    // Ändrar hundens status till utcheckad.
                    dog.setCheckedIn(false);

                    System.out.println("Hunden " + dog.getName()
                            + " har checkats ut!");
                }

                // 8. Avsluta programmet
            } else if (choice == 8) {

                // Avslutar huvudloopen.
                System.out.println("\nTack för att du besökte hunddagiset!");
                System.out.println("Välkommen åter!");
            }
        }

        // Stänger Scanner när programmet är färdigt.
        scanner.close();
    }

    /*
     * HJÄLPMETOD 1: Kontroll av menyval!
     *
     * Kontrollerar att användaren skriver ett heltal
     * mellan 1 och 8.
     *
     * Vid fel visas ett meddelande och frågan upprepas igen.
     */

    private static int readMenuChoice(Scanner scanner) {

        // while (true) upprepar frågan tills metoden returnerar ett värde.
        while (true) {

            System.out.print("Ditt val: ");

            // Läser hela raden som användaren skriver.
            String input = scanner.nextLine().trim();

            try {
                // Försöker omvandla texten till ett heltal.
                int choice = Integer.parseInt(input);

                // Godkänner endast menyval från 1 till 8.
                if (choice >= 1 && choice <= 8) {

                    // Skickar tillbaka det giltiga menyvalet.
                    return choice;
                }

                // Körs om talet exempelvis är 0 eller 9.
                System.out.println(
                        "Det valet fungerar inte, Välj gärna ett tal mellan 1 och 8.");

            } catch (NumberFormatException e) {

                // Körs om användaren exempelvis skulle råka skriva in hej eller 2.5 i menyvalet.
                System.out.println(
                        "Hoppsan! Skriv gärna ett heltal mellan 1 och 8.");
            }

            // Vid fel börjar loopen om och frågar efter ett nytt val.
        }
    }

    /*
     * HJÄLPMETOD 2: Kontroll av text!
     *
     * Kontrollerar att användaren inte lämnar ett textfält tomt.
     */

    private static String readNonEmpty(
            Scanner scanner, String prompt) {

        // Upprepar frågan tills användaren skriver text.
        while (true) {

            System.out.print(prompt);

            // trim() tar bort mellanslag i början och slutet.
            String input = scanner.nextLine().trim();

            // Om texten inte är tom skickas den tillbaka.
            if (!input.isEmpty()) {

                return input;
            }

            // Körs om användaren bara trycker på Enter
            // eller skriver enbart mellanslag.
            System.out.println("Du glömde att fylla i fältet. Försök gärna igen!");
        }
    }

    /*
     * HJÄLPMETOD 3: Kontroll av heltal!
     * Kontrollerar att användaren skriver ett heltal
     * som är minst lika stort som det angivna minimivärdet.
     */

    private static int readInt(
            Scanner scanner, String prompt, int min) {

        // Upprepar frågan tills ett giltigt heltal anges.
        while (true) {

            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            try {
                // Försöker omvandla texten till ett heltal.
                int number = Integer.parseInt(input);

                // Kontrollerar att talet uppfyller minimikravet.
                if (number >= min) {

                    return number;
                }

                // Körs om talet är mindre än min.
                System.out.println("Du behöver ange ett tal som är minst " + min + " Försök gärna igen!");

            } catch (NumberFormatException e) {

                // Körs om inmatningen inte är ett giltigt heltal.
                System.out.println("Det verkar inte vara ett heltal. Försök gärna igen!");
            }

            // Vid fel börjar loopen om och frågar igen, tills påståendet blir sant.
        }
    }

    /*
     * HJÄLPMETOD 4: Kontroll av JA och NEJ påståenden!
     * Godkänner endast J eller N.
     * Tillåter att både Stora och små bokstäver fungerar.
     */

    private static boolean readYesNo(
            Scanner scanner, String prompt) {

        // Upprepar frågan tills användaren svarar J eller N.
        while (true) {

            System.out.print(prompt);

            String input = scanner.nextLine().trim();

            // equalsIgnoreCase godkänner både J och j.
            if (input.equalsIgnoreCase("J")) {

                // true betyder ja.
                return true;
            }

            // Godkänner både N och n.
            if (input.equalsIgnoreCase("N")) {

                // false betyder nej.
                return false;
            }

            // Alla andra svar ger ett felmeddelande.
            System.out.println("Skriv gärna J för ja eller N för nej.");

            // Loopen börjar om och samma fråga ställs igen.
        }
    }
}