// 1. [] Skapa Namn?, Ange Ras?, Ange Chipnummer?,Ange kön?, Ålder?, Möjlighet till samsittning med andra hundar?
// Kastrerad? Pälsvård? Lång/Korhårig? syn/hörsel-nedsättning J/N?


// 2. [] Egenskaper? Blindhund? Narkotikasök? Lik hund? Sprängämnen? Personsök? Jägarhund?
// Tillgänglig för hjälp till polis eller missing peapol? Godkänner ägare utlåning?

//3.  [] Sjukdom/Allergi? J/N?


1. Så fungerar programmet
- Scanner tar emot det användaren skriver.
- choice sparar användarens menyval.
- while (choice != 0) gör att menyn visas igen tills användaren väljer 0.
- if och else if kontrollerar vilket alternativ användaren valt.
- hasNextInt() förhindrar att programmet kraschar om någon skriver bokstäver i stället för ett nummer.

Nästa steg
När du kör programmet ska menyn visas och du ska kunna välja mellan alternativen. Just nu skriver varje menyval
bara ut en text. Nästa steg blir att få menyval 1 – Registrera hund – att fungera på riktigt, 
så att användaren kan mata in namn, ras, chipnummer, kön, ålder och övriga uppgifter och spara hunden i registret.

Förklaring:
- ArrayList<Dog> är listan där alla hundar sparas.
- private skyddar listan från att ändras direkt från andra klasser.
- addDog(Dog dog) tar emot en hund och lägger till den i listan.

2. Uppdatera Main.java
   Vi ska nu skapa vårt hundregister och lägga till registreringen under menyval 1.

Direkt efter:
Scanner scanner = new Scanner(System.in);


lägger du till:
DogRegister register = new DogRegister();


3. Lägger till ny metod:
  Vad gör den nya metoden?

- dogs.isEmpty() kontrollerar om listan är tom.
- for (Dog dog : dogs) går igenom alla registrerade hundar.
- dog.showInfo() hämtar och skriver ut hundens uppgifter från Dog.java.


Vi öppnade upp följande rad i konstruktorn – Dog.java
Sedan Letade vi upp följande rad i konstruktorn:

this.checkedIn = false; 

Därefter ändrade vi den raden till

this.checkedIn = true;



4. Lade till funktion för att söka efter specifik hund via namn eller chipnummer.

Steg 1 – Vi uppdaterade "DogRegister.java"

Från:

    public Dog searchDog(String search) 

    for (Dog dog : dogs) 

        if (dog.getName().equalsIgnoreCase(search) ||
            dog.getChipNumber().equals(search)) 

            return dog;
        
         return null;

Till:

    else if (choice == 3) 

    System.out.println("\n--- SÖK EFTER HUND ---");

    System.out.print("Ange namn eller chipnummer: ");
    String search = scanner.nextLine().trim();

    Dog dog = register.searchDog(search);

    if (dog != null) {
        System.out.println("Hunden hittades!");
        dog.showInfo();
     else 
        System.out.println("Ingen hund hittades!");
    

Förklaring:
- for går igenom alla hundar i registret.
- getName() hämtar hundens namn.
- equalsIgnoreCase() gör att både enzo och Enzo fungerar.
- getChipNumber() gör det möjligt att söka med chipnummer.
- return dog skickar tillbaka hunden som hittades.
- return null betyder att ingen hund hittades.
  Steg 2 – Uppdatera Main.java
  Leta upp menyval 3:
  else if (choice == 3) 
  Dog dog = searchDog();
  if (dog != null) 
  dog.showInfo();
  

