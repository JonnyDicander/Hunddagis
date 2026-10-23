

public class Dog {

    // Hundens uppgifter
    private String name;
    private String breed;
    private String chipNumber;
    private String gender;
    private int age;
    private boolean canSitWithOthers;
    private boolean neutered;
    private boolean needsGrooming;
    private boolean visionImpaired;
    private boolean hearingImpaired;
    private boolean checkedIn;

    // Konstruktor - skapar en ny hund
    public Dog(String name, String breed, String chipNumber,
               String gender, int age, boolean canSitWithOthers,
               boolean neutered, boolean needsGrooming,
               boolean visionImpaired, boolean hearingImpaired) {

        this.name = name;
        this.breed = breed;
        this.chipNumber = chipNumber;
        this.gender = gender;
        this.age = age;
        this.canSitWithOthers = canSitWithOthers;
        this.neutered = neutered;
        this.needsGrooming = needsGrooming;
        this.visionImpaired = visionImpaired;
        this.hearingImpaired = hearingImpaired;

        // Hunden checkas inte in automatiskt
        this.checkedIn = false;
    }

    // Hämtar hundens namn
    public String getName() {
        return name;
    }

    // Hämtar hundens chipnummer
    public String getChipNumber() {
        return chipNumber;
    }

    // Kontrollerar om hunden är incheckad
    public boolean isCheckedIn() {
        return checkedIn;
    }

    // Checkar in eller ut hunden
    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }

    // Ändrar hundens uppgifter
    public void updateInfo(String name, String breed,
                           String gender, int age,
                           boolean canSitWithOthers,
                           boolean neutered, boolean needsGrooming,
                           boolean visionImpaired,
                           boolean hearingImpaired) {

        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.canSitWithOthers = canSitWithOthers;
        this.neutered = neutered;
        this.needsGrooming = needsGrooming;
        this.visionImpaired = visionImpaired;
        this.hearingImpaired = hearingImpaired;
    }

    // Visar hundens alla uppgifter
    public void showInfo() {

        System.out.println("\n--- HUNDENS UPPGIFTER ---");

        System.out.println("Namn: " + name);
        System.out.println("Ras: " + breed);
        System.out.println("Chipnummer: " + chipNumber);
        System.out.println("Kön: " +
                (gender.equalsIgnoreCase("H") ? "Hane" : "Tik"));
        System.out.println("Ålder: " + age + " år");

        System.out.println("Samsittning: " +
                (canSitWithOthers ? "Ja" : "Nej"));

        System.out.println("Kastrerad: " +
                (neutered ? "Ja" : "Nej"));

        System.out.println("Pälsvård: " +
                (needsGrooming ? "Ja" : "Nej"));

        System.out.println("Synnedsättning: " +
                (visionImpaired ? "Ja" : "Nej"));

        System.out.println("Hörselnedsättning: " +
                (hearingImpaired ? "Ja" : "Nej"));

        System.out.println("Incheckad: " +
                (checkedIn ? "Ja" : "Nej"));

        System.out.println("--------------------------");
    }
}
