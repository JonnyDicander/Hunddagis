
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        DogRegister register = new DogRegister();

        int choice = -1;

        while (choice != 0) {

            // Huvudmeny
            System.out.println("\n===== HUNDDAGIS =====");
            System.out.println("1. Registrera hund");
            System.out.println("2. Visa alla hundar");
            System.out.println("3. Sök efter hund");
            System.out.println("4. Ändra hunduppgifter");
            System.out.println("5. Ta bort hund");
            System.out.println("6. Checka in hund");
            System.out.println("7. Checka ut hund");
            System.out.println("0. Avsluta");
            System.out.println("=====================");

            System.out.print("Ditt val: ");

            // Kontrollera menyval
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Ange ett giltigt nummer!");
                scanner.nextLine();
                continue;
            }

            // 1. Registrera hund
            if (choice == 1) {

                System.out.println("\n--- REGISTRERA HUND ---");

                System.out.print("Ange namn: ");
                String name = scanner.nextLine();

                System.out.print("Ange ras: ");
                String breed = scanner.nextLine();

                System.out.print("Ange chipnummer: ");
                String chipNumber = scanner.nextLine();

                System.out.print("Ange kön: ");
                String gender = scanner.nextLine();

                System.out.print("Ange ålder: ");
                int age;

                if (scanner.hasNextInt()) {
                    age = scanner.nextInt();
                    scanner.nextLine();
                } else {
                    System.out.println("Ogiltig ålder!");
                    scanner.nextLine();
                    continue;
                }

                System.out.print("Kan sitta med andra hundar? (J/N): ");
                boolean canSitWithOthers =
                        scanner.nextLine().equalsIgnoreCase("J");

                System.out.print("Kastrerad? (J/N): ");
                boolean neutered =
                        scanner.nextLine().equalsIgnoreCase("J");

                System.out.print("Behöver pälsvård? (J/N): ");
                boolean needsGrooming =
                        scanner.nextLine().equalsIgnoreCase("J");

                System.out.print("Långhårig eller korthårig?: ");
                String coatType = scanner.nextLine();

                System.out.print("Synnedsättning? (J/N): ");
                boolean visionImpaired =
                        scanner.nextLine().equalsIgnoreCase("J");

                System.out.print("Hörselnedsättning? (J/N): ");
                boolean hearingImpaired =
                        scanner.nextLine().equalsIgnoreCase("J");

                // Skapa hundobjekt
                Dog dog = new Dog(
                        name,
                        breed,
                        chipNumber,
                        gender,
                        age,
                        canSitWithOthers,
                        neutered,
                        needsGrooming,
                        coatType,
                        visionImpaired,
                        hearingImpaired
                );

                // Lägg till hund i registret
                register.addDog(dog);

                // 2. Visa alla hundar
            } else if (choice == 2) {

                register.showAllDogs();

                // 3. Sök efter hund
            } else if (choice == 3) {

                System.out.println("\n--- SÖK EFTER HUND ---");

                System.out.print("Ange namn eller chipnummer: ");
                String search = scanner.nextLine().trim();

                Dog dog = register.searchDog(search);

                if (dog != null) {
                    System.out.println("\nHunden hittades!");
                    dog.showInfo();
                } else {
                    System.out.println("Ingen hund hittades!");
                }

                // 4. Ändra hunduppgifter
            } else if (choice == 4) {
                System.out.println("Ändra hunduppgifter");

                System.out.print("Ange hundens chipnummer: ");

                String chipNumber = scanner.nextLine();

                Dog dog = register.findDog(chipNumber);

                if (dog == null) {
                    System.out.println("Hunden hittades inte.");
                } else {
                    System.out.print("Nytt namn: ");
                    String name = scanner.nextLine();

                    System.out.print("Ny ras: ");
                    String breed = scanner.nextLine();

                    System.out.print("Ny ålder: ");
                    int age = Integer.parseInt(scanner.nextLine());

                    System.out.print("Nytt kön: ");
                    String gender = scanner.nextLine();

                    System.out.print("Ny pälstyp: ");
                    String coatType = scanner.nextLine();

                    System.out.print("Kan vara med andra hundar? (J/N): ");
                    boolean canSitWithOthers =
                            scanner.nextLine().equalsIgnoreCase("J");

                    System.out.print("Kastrerad? (J/N): ");
                    boolean neutered =
                            scanner.nextLine().equalsIgnoreCase("J");

                    System.out.print("Behöver pälsvård? (J/N): ");
                    boolean needsGrooming =
                            scanner.nextLine().equalsIgnoreCase("J");

                    System.out.print("Nedsatt syn? (J/N): ");
                    boolean visionImpaired =
                            scanner.nextLine().equalsIgnoreCase("J");

                    System.out.print("Nedsatt hörsel? (J/N): ");
                    boolean hearingImpaired =
                            scanner.nextLine().equalsIgnoreCase("J");

                    dog.updateInfo(
                            name, breed, gender, age,
                            canSitWithOthers, neutered, needsGrooming,
                            coatType, visionImpaired, hearingImpaired
                    );
                    System.out.println("Hundens uppgifter har uppdaterats!");
                }

            // 5. Ta bort hund
        } else if (choice == 5) {

                System.out.print("Ange hundens chipnummer: ");
                String chipNumber = scanner.nextLine();

                Dog dog = register.findDog(chipNumber);

                if (dog == null) {
                    System.out.println("Hunden hittades inte.");
                } else {
                    System.out.println("Du har valt att ta bort:");
                    dog.showInfo();

                    System.out.print("Är du säker? (J/N): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("J")) {
                        register.removeDog(chipNumber);
                    } else {
                        System.out.println("Borttagningen avbröts.");
                    }
                }

                // 6. Checka in hund
            } else if (choice == 6) {

                System.out.println("\n--- CHECKA IN HUND ---");

                System.out.print("Ange hundens chipnummer: ");
                String chipNumber = scanner.nextLine();

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


                // 7. Checka ut hund
            } else if (choice == 7) {

                System.out.println("\n--- CHECKA UT HUND ---");

                System.out.print("Ange hundens chipnummer: ");
                String chipNumber = scanner.nextLine();

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




                // 0. Avsluta
            } else if (choice == 0) {

                System.out.println("Programmet avslutas!");

            } else {

                System.out.println("Ogiltigt val!");
            }
        }

        scanner.close();
    }
}

