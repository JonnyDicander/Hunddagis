// 1. [] Skapa Namn?, Ange Ras?, Ange Chipnummer? Ange kön?, Ålder?, Möjlighet till samsittning med andra hundar?
// Kastrerad? Pälsvård? Lång/Korthårig? Syn/hörsel-nedsättning J/N?


// 2. [] Egenskaper? Blindhund? Narkotikasökande? Lik hund? Sprängämnen? Personsök? Jägarhund?
// Tillgänglig för hjälp till polis eller missing people? Godkänner ägare utlåning? Tjänstehundar? 

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
3. 
Direkt efter:
-Scanner scanner = new Scanner(System.in);

Lägger du till:
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


Förbättrad felhantering i Main.java:

- Jag har förbättrat felhanteringen i Main.java för att göra programmet enklare och tydligare att använda.
- Kontroll av menyval: Användaren kan välja mellan alternativ 1–8. Om användaren skriver ett ogiltigt val får hen ett vänligt felmeddelande och får försöka igen.
- Kontroll av inmatning: Programmet kontrollerar att användaren anger giltiga heltal, inte lämnar textfält tomma och svarar med J eller N på ja- och nej-frågor.
- Upprepade frågor: Med hjälp av while-loopar upprepas frågorna tills användaren anger ett giltigt svar.
- Välkomst- och avslutningsmeddelanden: Programmet visar ett välkomstmeddelande när det startar och ett tackmeddelande med ”Välkommen åter!” när användaren väljer att avsluta med alternativ 8.
- Syftet med ändringarna är att göra programmet mer användarvänligt och minska risken för att felaktig inmatning stör programmets körning.




# Hunddagis – Mina ändringar

## Förbättrad registrering och felhantering

Jag har arbetat med att förbättra registreringen av hundar och gjort programmet mer användarvänligt.

### Ändringar jag har gjort

- **Namn och ras:** Kontrollerar att användaren skriver text och inte siffror.
- **Chipnummer:** Accepterar endast siffror och kontrollerar att numret inte redan finns registrerat.
- **Kön:** Användaren kan endast välja H (hane) eller T (tik), Har även lagt till direkt i frågan vad H/T står för.
- **Ålder:** Måste vara ett heltal mellan 0 och 25.
- **Ja/nej-frågor:** Accepterar endast J eller N.
- **Pälslängd:** Tagit bort frågan eftersom vi redan frågar om hunden behöver pälsvård.
- **Felhantering:** Vid felaktig inmatning visas ett felmeddelande och frågan upprepas utan att programmet kraschar.

### Ändrade filer

- `Main.java` – Förbättrad inmatningskontroll och felhantering.
- `Dog.java` – Tagit bort onödig information om pälslängd.
- `DogRegister.java` – Befintlig kontroll av dubbla chipnummer används.

### Förslag på förbättring kan vara att när man vill ändra en uppgift på en registrerad hund så kan man välja
i en lista vilken punkt man vill förändra, då slipper man gå igenom alla frågor igen när man kanske bara vill ändra kön
eller en allergi.



