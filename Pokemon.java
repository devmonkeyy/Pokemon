import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.util.Iterator;
/**
 * Simple Pokemon game played on the terminal.
 *
 * @author devmonkeyy
 * @version 5/23/23
 */
public class Pokemon
{
    private String name;
    private Object starter;
    private int battlesWon = 0;
    // private boolean wonChampionship;
    HashMap<String, Object[]> pokemonHashMap = new HashMap<String, Object[]>();
    private int[] pokemonXPLevels = new int[]{1000, 2500, 5000, 10000, 25000, 50000, 75000, 100000};
    
    
    /** 
     * Empty constructor for Pokemon methods
     */
    public Pokemon() {
    
    }
    
    /**
     * Overloaded constructor for objects of the class Pokemon.
     * 
     * @param  name  User's name
     */
    public Pokemon(String name) throws InterruptedException {
        this.name = name;
        this.starter = ChooseStarter();
        this.pokemonHashMap = pokemonHashMap;
        System.out.println("Congratulations! You got " + getStarter() + "!");
        System.out.println();
        System.out.println("________________________________");
        this.battlesWon = 0;
        System.out.println();
        firstCatch();
        System.out.println();
        Thread.sleep(2000);
        System.out.println("________________________________");
        System.out.println("Tip: You can use the pokemon command to access your current pokemon!");
        System.out.println("Enter continue to continue your journey!");
        System.out.println("Win battles to gain XP! Once you get to level 10 you qualify for the Pokemon Championships.");
        System.out.println("Catch as many Pokemon as you can.");
        System.out.println("Gotta catch em' all!");
        System.out.println("________________________________");
        Thread.sleep(2000);
        System.out.println();
        System.out.println();
        
        
        while (endGame()) {
            journey();
            System.out.println();
            System.out.println();
            Thread.sleep(2000);
        }
    }
    
    /**
     * A getter for the name of the user. 
     * 
     * @return the user's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * A getter for the user's Starter Pokemon.
     * 
     * @return the user's starter Pokemon
     */
    public Object getStarter() {
        return starter;
    }

    
    /**
     * Displays your current Pokemon.
     */
    public void displayPokemon() {
        System.out.println("________________________________");
        System.out.println("Your Current Pokemon");
        System.out.println("________________________________");
        
        for (HashMap.Entry<String, Object[]> entry : pokemonHashMap.entrySet()) {
            String pokemonKey = entry.getKey();
            Object[] pokemonDetails = entry.getValue();
        
            System.out.println("Pokemon: " + pokemonKey);
            System.out.println("Type: " + pokemonDetails[0]);
            System.out.println("Level: " + pokemonDetails[1]);
            System.out.println("XP: " + pokemonDetails[2]);
            System.out.println("________________________________");
        }    
    }
    
    
    /**
     * Getter for the starter stats
     * 
     * @return stats of chosen starter
     */
    public String ChooseStarter() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
    
        System.out.println("Choose your starter! Gotta Catch 'Em All! ");
        System.out.println("Charmander: Fire Type");
        System.out.println("Squirtle: Water Type");
        System.out.println("Bulbasaur: Leaf Type");
        Thread.sleep(2000);
        
        while (true) {
            String starter = sc.nextLine().toLowerCase();
        
            String[] options = new String[]{"bulbasaur", "squirtle", "charmander"};
            
            stop(starter);
            
            for (int i = 0; i < options.length; i++) {
                if (options[i].equals(starter)) {
                    sc.close();
                    if (starter.equals("charmander")) {
                        Object[] starterStats = new Object[]{"Fire", 1, 1000} ;
                        pokemonHashMap.put("Charmander", starterStats);
                    }
                    else if (starter.equals("squirtle")) {
                        Object[] starterStats = new Object[]{"Water", 1, 1000} ;
                        pokemonHashMap.put("Squirtle", starterStats);
                    }
                    else if (starter.equals("bulbasaur")) {
                        Object[] starterStats = new Object[]{"Leaf", 1, 1000} ;
                        pokemonHashMap.put("Bulbasaur", starterStats);
                    }
                    return starter.substring(0, 1).toUpperCase() + starter.substring(1);
                }
            }
            System.out.println("You didn't enter an available starter! Try again!");
        }
    }
    
    
    /**
     * First catch of Pokemon journey. Adds the Pokemon to the Hash Map.
     */
    public void firstCatch() throws InterruptedException {
        Scanner sc = new Scanner(System.in); 
        
        HashMap<String, String> wildPokemon = new HashMap<String, String>();
        String dunsparceData = "Leaf";
        wildPokemon.put("Dunsparce", dunsparceData);
        String psyduckData = "Water";
        wildPokemon.put("Psyduck", psyduckData);
        String vulpixData = "Fire";
        wildPokemon.put("Vulpix", vulpixData);

        
        int rnd = new Random().nextInt(wildPokemon.size());
        
        String pokemonType = wildPokemon.get(wildPokemon.keySet().toArray()[rnd]);
        String pokemon = "";
        
        for (HashMap.Entry<String, String> entry : wildPokemon.entrySet()) {
            if (entry.getValue() == pokemonType) {
                pokemon = entry.getKey();
                break;
            }
        }

        
        System.out.println("Welcome to the Pokeverse " + name + "! You spot a wild " + pokemon + "!");
        Thread.sleep(2000);
        System.out.println("Enter catch to catch... Gotta Catch 'Em All");
        
        while (true) {
            String command = sc.nextLine().toLowerCase();
            
            stop(command);
            
            if (command.equals("catch")) {
                System.out.println("You caught " + pokemon + "(" + pokemonType + " Type)!");
                //Level, XP
                Object[] pokemonStats = {pokemonType, 1, 1000};
                pokemonHashMap.put(pokemon, pokemonStats);
                break;
            }
            
            System.out.println("You didn't enter catch!");
        }
    }
    
    /**
     * Randomly selects battle or catch pokemon, with slight bias towards battle.
     */
    public void journey() throws InterruptedException {
        Random r = new Random();
        int low = 1;
        int high = 7;
        int result = r.nextInt(high-low) + low;
        if(result % 2 == 1) {
            battle();
        }
        else {
            catchPokemon();
        }
    }
    
    /**
     * Simulates a battle with a random trainer and Pokemon. 
     */
    public void battle() throws InterruptedException {
        Scanner sc = new Scanner(System.in); 
        
        System.out.println("________________________________");
        String message1 = generateMessage1();  
        System.out.println(message1);

        Object[] randomPokemon = generateMessage2Variables();
        
        String trainerPokemonName = (String)randomPokemon[0];
        String trainerPokemonType = (String)randomPokemon[1];
        int trainerPokemonLevel = (int)randomPokemon[2];
        String message2 = "The trainer has Pokemon " + 
                        trainerPokemonName + 
                        ", Type: " + 
                        trainerPokemonType +
                        ", Level: " + 
                        trainerPokemonLevel;
        System.out.println(message2);
        System.out.println("________________________________");
        
        Thread.sleep(2000);
        
        displayPokemon();
        System.out.println("Enter the name of the pokemon you choose.");
        
        Thread.sleep(2000);
        
        outerloop:
        while (true) {
            String command = sc.nextLine().toLowerCase();
            
            stop(command);
            
            for (HashMap.Entry entry : pokemonHashMap.entrySet())
            {
                String entryPokemon = (String)entry.getKey();
                if (entryPokemon.toLowerCase().equals(command)) {
                    String pokemonType = "";
                    int pokemonLevel = 1;
                    int pokemonXP = 0;
                    
                    Object[] pokemonDetails = (Object[])entry.getValue();
                    
                    pokemonType = (String)pokemonDetails[0];
                    pokemonLevel = (int)pokemonDetails[1];
                    pokemonXP = (int)pokemonDetails[2];
                    
                    String result = battleMechanics(command, pokemonType, pokemonLevel, pokemonXP,
                                    trainerPokemonName, trainerPokemonType, trainerPokemonLevel);
                    System.out.println("________________________________");
                    if (result.equals("Win")) {
                        battlesWon += 1;
                        pokemonXP += 1000;
                        pokemonLevel = pokemonLevelCalculator(pokemonXP);
                        pokemonHashMap.replace(entryPokemon,new Object[]{pokemonType, pokemonLevel, pokemonXP});
                        System.out.println("Battle Result: Win! Your " + entryPokemon + " has gained 1000 XP!");
                    }  
                    else if (result.equals("Loss")) {
                        System.out.println("Battle Result: Loss!");
                    }      
                    else if (result.equals("Tie")) {
                        pokemonXP += 500;
                        pokemonLevel = pokemonLevelCalculator(pokemonXP);
                        pokemonHashMap.replace(entryPokemon,new Object[]{pokemonType, pokemonLevel, pokemonXP});
                        System.out.println("Battle Result: Tie! Your " + entryPokemon + " has gained 500 XP!");
                    }  
                    System.out.println("________________________________");
                    
                    
                    break outerloop;
                }
            }
            
            System.out.println("________________________________");
            System.out.println("You didn't enter a Pokemon in your inventory! Try again.");
            System.out.println("________________________________");
        }
    }
    
    /**
     * Getter that returns a random encountering message with a random trainer.
     * 
     * @return random message
     */
    public String generateMessage1() {
        String[] trainers = new String[]{"Brock", 
            "Misty", "Lt. Surge", "Erika", 
            "Koga", "Sabrina", "Blaine", 
            "Giovanni", "Lorelei", "Bruno"};
            
        int rnd = new Random().nextInt(trainers.length);
        String trainer = trainers[rnd];
        
        String[] messages = new String[]{"You've encountered Trainer " + trainer + "!",
                "Trainer " + trainer + " has appeared!",
                "Look, there's Trainer " + trainer + "!",
                "Oh, it seems you've come across Trainer " + trainer + "!",
                "You've stumbled upon Trainer " + trainer + "!",
                "Trainer " + trainer + " challenges you to a battle!",
                "A rival trainer, " + trainer + ", has crossed your path!",
                "Prepare for an encounter with Trainer " + trainer + "!",
                "You've found Trainer " + trainer + " in your way!",
                "Get ready! Trainer " + trainer + " is here to challenge you!"};
        
        int rnd2 = new Random().nextInt(messages.length);
        String message1 = messages[rnd2];   
        return message1;
    }
    
    /**
     * Get a random pokemon's stats
     * 
     * @return random pokemon's stats
     */
    public Object[] generateMessage2Variables() {
        HashMap<String, Object[]> trainerPokemon = new HashMap<String, Object[]>();
    
        int level = 1;
        int minLevel = 5;
        int maxLevel = 1;
        
        for (Object[] stats : pokemonHashMap.values()) {
            int statLevel = (int)stats[1];
            if (statLevel < minLevel) {
                minLevel = statLevel;
            }
            else if (statLevel > maxLevel) {
                maxLevel = statLevel;
            }
        }
        
        Random r = new Random();
        int low = minLevel;
        int high = maxLevel + 1;
        level = r.nextInt(high-low) + low;
        
        // Fire Pokemon
        trainerPokemon.put("Fire1", new Object[]{"Charmander", "Fire", level});
        trainerPokemon.put("Fire2", new Object[]{"Vulpix", "Fire", level});
        trainerPokemon.put("Fire3", new Object[]{"Growlithe", "Fire", level});
        trainerPokemon.put("Fire4", new Object[]{"Ponyta", "Fire", level});
        trainerPokemon.put("Fire5", new Object[]{"Cyndaquil", "Fire", level});
        trainerPokemon.put("Fire6", new Object[]{"Slugma", "Fire", level});
        trainerPokemon.put("Fire7", new Object[]{"Torchic", "Fire", level});
        trainerPokemon.put("Fire8", new Object[]{"Chimchar", "Fire", level});

        // Leaf Pokemon
        trainerPokemon.put("Leaf1", new Object[]{"Bulbasaur", "Leaf", level});
        trainerPokemon.put("Leaf2", new Object[]{"Oddish", "Leaf", level});
        trainerPokemon.put("Leaf3", new Object[]{"Bellsprout", "Leaf", level});
        trainerPokemon.put("Leaf4", new Object[]{"Chikorita", "Leaf", level});
        trainerPokemon.put("Leaf5", new Object[]{"Treecko", "Leaf", level});
        trainerPokemon.put("Leaf6", new Object[]{"Seedot", "Leaf", level});
        trainerPokemon.put("Leaf7", new Object[]{"Snivy", "Leaf", level});
        trainerPokemon.put("Leaf8", new Object[]{"Turtwig", "Leaf", level});

        // Water Pokemon
        trainerPokemon.put("Water1", new Object[]{"Squirtle", "Water", 1});
        trainerPokemon.put("Water2", new Object[]{"Psyduck", "Water", 1});
        trainerPokemon.put("Water3", new Object[]{"Poliwag", "Water", 1});
        trainerPokemon.put("Water4", new Object[]{"Totodile", "Water", 1});
        trainerPokemon.put("Water5", new Object[]{"Mudkip", "Water", 1});
        trainerPokemon.put("Water6", new Object[]{"Lotad", "Water", 1});
        trainerPokemon.put("Water7", new Object[]{"Oshawott", "Water", 1});
        trainerPokemon.put("Water8", new Object[]{"Piplup", "Water", 1});
        
        int randomIndex = r.nextInt(trainerPokemon.size());
        String randomKey = (String) trainerPokemon.keySet().toArray()[randomIndex];
        
        Object[] randomPokemon = trainerPokemon.get(randomKey);
        
        return randomPokemon;
    }
    
    /**
     * Getter for battle result
     * 
     * @return "Win", "Loss", or "Tie"
     */
    public String battleMechanics(String pokemonName, String pokemonType, int pokemonLevel, int pokemonXP,
                                String trainerPokemonName, String trainerPokemonType, int trainerPokemonLevel) {
        System.out.println("________________________________");
        System.out.println(pokemonName + ", Type: " + pokemonType + ", Level: " + pokemonLevel + "(XP: " + pokemonXP + ")");
        System.out.println("...VS...");
        System.out.println(trainerPokemonName + ", Type: " +  trainerPokemonType + ", Level: " + trainerPokemonLevel);
        System.out.println("________________________________");
        
        int score = 0;
        int trainerScore = 0;
        
        if (pokemonType.equals("Water") && trainerPokemonType.equals("Fire")) {
            score += 1;
        }
        if (pokemonType.equals("Leaf") && trainerPokemonType.equals("Water")) {
            score += 1;
        }
        if (pokemonType.equals("Fire") && trainerPokemonType.equals("Leaf")) {
            score += 1;
        }
        else if (pokemonType.equals("Fire") && trainerPokemonType.equals("Water")) {
            trainerScore += 1;
        }
        else if (pokemonType.equals("Water") && trainerPokemonType.equals("Leaf")) {
            trainerScore += 1;
        }
        else if (pokemonType.equals("Leaf") && trainerPokemonType.equals("Fire")) {
            trainerScore += 1;
        }
        
        if (pokemonLevel > trainerPokemonLevel) {
            score *= ((pokemonLevel - trainerPokemonLevel)/10);
        }
        else if (trainerPokemonLevel > pokemonLevel) {
            trainerScore += ((trainerPokemonLevel - pokemonLevel)/10);
        }
        
        if (score > trainerScore) {
            return "Win";
        }
        else if (trainerScore > score) {
            return "Loss";
        }
        else {
            return "Tie";
        }
    }
    
    /**
     * Getter for new Level based on XP.
     * 
     * @return calculated level
     */
    public int pokemonLevelCalculator(int pokemonXP) {
        int calculatedLevel = 1;
        if (pokemonXP < 100000) {
            for (int i = 0; i < pokemonXPLevels.length; i++) {
                if (pokemonXPLevels[i] > pokemonXP) {
                    calculatedLevel = i;
                    break;
                }
            }
        }
        else {
            calculatedLevel = 8;
        }
        return calculatedLevel;
    }
    
    /**
     * Simulates a catching situation
     */
    public void catchPokemon() {
        Scanner sc = new Scanner(System.in); 
        
        System.out.println("________________________________");
        String[] wildPokemonData = generateWildPokemon();
        String pokemonName = wildPokemonData[0];
        String pokemonType = wildPokemonData[1];
        String catchMessage1 = generateCatchMessage1(pokemonName, pokemonType);
        System.out.println(catchMessage1);
        System.out.println("Enter catch to catch. Gotta Catch Em' All!");
        System.out.println("________________________________");
        
        while (true) {
            String command = sc.nextLine().toLowerCase();
            
            stop(command);
            
            if (command.equals("catch")) {
                break;
            }
        }
        
        String catchResult = catchMechanics();
        if (catchResult.equals("Caught")) {
            Object[] pokemonStats = {pokemonType, 1, 1000};
                pokemonHashMap.put(pokemonName, pokemonStats);
            System.out.println("You caught " + pokemonName +", Type "+ pokemonType + "!");
        }
        else {
            System.out.println("Better Luck Next Time!");
        }
    }
    
    /**
     * Getter for result of catching situation. User is given three chances, with randomized results.
     * 
     * @return "Caught" or "Not Caught"
     */
    public String catchMechanics() {
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            int randomNumber = r.nextInt(2);
            if (randomNumber == 0) {
                return "Caught";
            }
        }
        return "Not Caught";
    }
    
    /**
     * Getter for the stats of a random wild Pokemon.
     * 
     * @return a random wild Pokemon's stats
     */
    public String[] generateWildPokemon() {
        HashMap<String, String> wildPokemon = new HashMap<>();
        
        // Fire Pokemon 
        wildPokemon.put("Charizard", "Fire");
        wildPokemon.put("Arcanine", "Fire");
        wildPokemon.put("Blaziken", "Fire");
        wildPokemon.put("Magmar", "Fire");
        wildPokemon.put("Infernape", "Fire");
        wildPokemon.put("Heatran", "Fire");
        wildPokemon.put("Emboar", "Fire");
        wildPokemon.put("Volcarona", "Fire");
        wildPokemon.put("Chandelure", "Fire");
        wildPokemon.put("Incineroar", "Fire");

        // Water Pokemon
        wildPokemon.put("Blastoise", "Water");
        wildPokemon.put("Gyarados", "Water");
        wildPokemon.put("Swampert", "Water");
        wildPokemon.put("Lapras", "Water");
        wildPokemon.put("Kingdra", "Water");
        wildPokemon.put("Milotic", "Water");
        wildPokemon.put("Samurott", "Water");
        wildPokemon.put("Carracosta", "Water");
        wildPokemon.put("Greninja", "Water");
        wildPokemon.put("Primarina", "Water");

        // Leaf Pokemon
        wildPokemon.put("Venusaur", "Leaf");
        wildPokemon.put("Exeggutor", "Leaf");
        wildPokemon.put("Sceptile", "Leaf");
        wildPokemon.put("Leafeon", "Leaf");
        wildPokemon.put("Roserade", "Leaf");
        wildPokemon.put("Tangrowth", "Leaf");
        wildPokemon.put("Serperior", "Leaf");
        wildPokemon.put("Virizion", "Leaf");
        wildPokemon.put("Decidueye", "Leaf");
        wildPokemon.put("Rillaboom", "Leaf");
        
        int rnd = new Random().nextInt(wildPokemon.size());
        
        String pokemonType = wildPokemon.get(wildPokemon.keySet().toArray()[rnd]);
        String pokemon = "";
        
        for (HashMap.Entry<String, String> entry : wildPokemon.entrySet()) {
            if (entry.getValue() == pokemonType) {
                pokemon = entry.getKey();
                break;
            }
        }
        
        String[] randomWildPokemon = new String[]{pokemon, pokemonType};

        
        return randomWildPokemon;
    }
    
    /**
     * Getter for a random encountering wild Pokemon message
     * 
     * @return random encountering wild Pokemon message
     */
    public String generateCatchMessage1(String pokemonName, String pokemonType) {
        String[] messages = new String[]{"You've encountered a wild " + pokemonName + "! It's a " + pokemonType + " type!",
                        "Look, there's a " + pokemonType + " Pokémon named " + pokemonName + "!",
                        "Oh, it seems you've come across a " + pokemonType + " Pokémon called " + pokemonName + "!",
                        "You've stumbled upon a " + pokemonType + " Pokémon known as " + pokemonName + "!",
                        "A wild " + pokemonName + " appeared! It's a " + pokemonType + " type Pokémon!",
                        "Behold! " + pokemonName + ", a magnificent " + pokemonType + " Pokémon, has crossed your path!",
                        "Prepare for an encounter with " + pokemonName + "! It's a " + pokemonType + " type Pokémon!",
                        "You've found a " + pokemonType + " Pokémon named " + pokemonName + " in your way!",
                        "Get ready! A " + pokemonType + " Pokémon named " + pokemonName + " is here to challenge you!",
                        "On your journey, you discovered a wild " + pokemonName + "! It belongs to the " + pokemonType + " type!"};
        
        int rnd = new Random().nextInt(messages.length);
        String message = messages[rnd];   
        
        return message;
    }
    
    /**
     * Getter for whether or not the game has ended, with an option to play again.
     * 
     * @return True if end game has not been achieved
     */
    public boolean endGame() throws InterruptedException {
        Scanner sc = new Scanner(System.in); 
        // if (wonChampionship) {
            // System.out.println("You beat the champion! You beat the game!");
            // Thread.sleep(2000);
            // System.out.println("Do you want to play again?");
            // System.exit(0);
        // }
        if (battlesWon >=  1000 || pokemonHashMap.size() >= 500) {
            if (battlesWon >= 1000) {
                System.out.println("You have 1000 trainers! You beat the game!");
            }
            else if (pokemonHashMap.size() >= 500) {
                System.out.println("You 500 Pokemon! You beat the game!");
            }
            Thread.sleep(2000);
            System.out.println("Do you want to play again? (y/n)");
            while (true) {
                String command = sc.nextLine().toLowerCase();
                if (command.equals("y") || command.equals("yes")) {
                    battlesWon = 0;
                    pokemonHashMap.clear();
                }
                else if (command.equals("n") || command.equals("no")) {
                    System.exit(0);
                }
            }
        }
        else if (pokemonHashMap.size() >= 500) {
            System.out.println("You 500 Pokemon! You beat the game!");
            Thread.sleep(2000);
            System.out.println("Do you want to play again?");
            System.exit(0);
        
        }
        
        return true;
    }
    
    /**
     * Stop game if person enters "stop" in the terminal.
     */
    public void stop(String command) {
        if (command.equals("stop")) {
            System.out.println("You decided to quit.");
            System.exit(0);
        }
    }
}
