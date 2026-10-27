
import java.time.LocalDateTime;

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
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

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

    // ==========================================
    // GETTERS - HÄMTAR HUNDENS UPPGIFTER
    // ==========================================

    public String getName() {
        return name;
    }

    public String getChipNumber() {
        return chipNumber;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public boolean isNeedsGrooming() {
        return needsGrooming;
    }

    public boolean isVisionImpaired() {
        return visionImpaired;
    }

    public boolean isHearingImpaired() {
        return hearingImpaired;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    // ==========================================
    // SETTERS - ÄNDRAR ENSKILDA UPPGIFTER
    // ==========================================

    // Ändrar hundens namn
    public void setName(String name) {
        this.name = name;
    }

    // Ändrar hundens ras
    public void setBreed(String breed) {
        this.breed = breed;
    }

    // Ändrar hundens ålder
    public void setAge(int age) {
        this.age = age;
    }

    // Ändrar hundens kön
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Ändrar om hunden kan sitta med andra hundar
    public void setCanSitWithOthers(boolean value) {
        this.canSitWithOthers = value;
    }

    // Ändrar om hunden är kastrerad
    public void setNeutered(boolean value) {
        this.neutered = value;
    }

    // Ändrar om hunden behöver pälsvård
    public void setNeedsGrooming(boolean value) {
        this.needsGrooming = value;
    }

    // Ändrar om hunden har synnedsättning
    public void setVisionImpaired(boolean value) {
        this.visionImpaired = value;
    }

    // Ändrar om hunden har hörselnedsättning
    public void setHearingImpaired(boolean value) {
        this.hearingImpaired = value;
    }

    // ==========================================
    // INCHECKNING OCH UTCHECKNING
    // ==========================================

    // Uppdaterar status och sparar datum och tid
    public void setCheckedIn(boolean checkedIn) {

        // Gör inget om statusen redan är densamma
        if (this.checkedIn == checkedIn) {
            return;
        }

        this.checkedIn = checkedIn;

        if (checkedIn) {
            checkInTime = LocalDateTime.now();
        } else {
            checkOutTime = LocalDateTime.now();
        }
    }

    // ==========================================
    // UPPDATERA ALLA UPPGIFTER
    // ==========================================

    // Behålls för kompatibilitet med gamla Main.java
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

    // ==========================================
    // VISA HUNDENS UPPGIFTER
    // ==========================================

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
