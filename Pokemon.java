import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.util.Iterator;
/**
 * Write a description of class Pokemon here.
 *
 * @author Ryan Zhang
 * @version 5/15/23
 */
public class Pokemon
{
    private String name;
    private int age;
    private Object starter;
    private int battlesWon;
    private int level;
    private boolean wonChampionship;
    HashMap<String, Object[]> pokemonHashMap = new HashMap<String, Object[]>();
    
    private int[] userLevels = new int[]{1, 2, 5, 10, 25};
    private int[] pokemonXPLevels = new int[]{1000, 2500, 5000, 10000, 25000};
    
    public Pokemon(String name, int age) {
        this.name = name;
        this.age = age;
        this.level = 0;
        this.wonChampionship = false;
        this.starter = ChooseStarter();
        this.pokemonHashMap = pokemonHashMap;
        System.out.println("Congratulations! You got " + getStarter() + "!");
        System.out.println();
        System.out.println("________________________________");
        this.battlesWon = 0;
        System.out.println();
        firstCatch();
        System.out.println();
        System.out.println("________________________________");
        System.out.println("Tip: You can use the pokemon command to access your current pokemon!");
        System.out.println("Enter continue to continue your journey!");
        System.out.println("Win battles to gain XP! Once you get to level 10 you qualify for the Pokemon Championships.");
        System.out.println("Catch as many Pokemon as you can.");
        System.out.println("Gotta catch em' all!");
        System.out.println("________________________________");
        System.out.println();
        System.out.println();
        
        
        while (endGame()) {
            journey();
            System.out.println();
            System.out.println();
        }
    }
    
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public Object getStarter() {
        return starter;
    }

    
    public void getPokemon() {
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
    
    public Object ChooseStarter() {
        Scanner sc = new Scanner(System.in);
    
        System.out.println("Choose your starter! Gotta Catch 'Em All! ");
        System.out.println("Charmander: Fire Type");
        System.out.println("Squirtle: Water Type");
        System.out.println("Bulbasaur: Leaf Type");
        
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
                        return starterStats;
                    }
                    else if (starter.equals("squirtle")) {
                        Object[] starterStats = new Object[]{"Water", 1, 1000} ;
                        pokemonHashMap.put("Squirtle", starterStats);
                        return starterStats;
                    }
                    else if (starter.equals("bulbasaur")) {
                        Object[] starterStats = new Object[]{"Leaf", 1, 1000} ;
                        pokemonHashMap.put("Bulbasaur", starterStats);
                        return starterStats;
                    }
                }
            }
            System.out.println("You didn't enter an available starter! Try again!");
        }
    }
    
    public void firstCatch() {
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
        String pokemon = null;
        
        for (HashMap.Entry<String, String> entry : wildPokemon.entrySet()) {
            if (entry.getValue() == pokemonType) {
                pokemon = entry.getKey();
                break;
            }
        }

        
        System.out.println("Welcome to the Pokeverse " + name + "! You spot a wild " + pokemon + "!");
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
    
    public void journey() {
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
    
    public void battle() {
        Scanner sc = new Scanner(System.in); 
        
        System.out.println("________________________________");
        String message1 = generateMessage1();  
        System.out.println(message1);

        Object[] randomPokemon = generateMessage2();
        
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
        
        
        getPokemon();
        System.out.println("Enter the name of the pokemon you choose.");
        
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
                    if (result.equals("Win")) {
                        battlesWon += 1;
                        pokemonXP += 1000;
                        pokemonLevel = pokemonLevelCalculator(pokemonXP);
                        pokemonHashMap.replace(entryPokemon,new Object[]{pokemonType, pokemonLevel, pokemonXP});
                        System.out.println("Battle Result: Win!");
                    }  
                    else if (result.equals("Loss")) {
                        System.out.println("Battle Result: Loss!");
                    }      
                    else if (result.equals("Tie")) {
                        pokemonXP += 500;
                        pokemonLevel = pokemonLevelCalculator(pokemonXP);
                        pokemonHashMap.replace(entryPokemon,new Object[]{pokemonType, pokemonLevel, pokemonXP});
                        System.out.println("Battle Result: Tie!");
                    }      
                    
                    
                    break outerloop;
                }
            }
            
            System.out.println("________________________________");
            System.out.println("You didn't enter a Pokemon in your inventory! Try again.");
            System.out.println("________________________________");
        }
    }
    
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
    
    public Object[] generateMessage2() {
        HashMap<String, Object[]> trainerPokemon = new HashMap<String, Object[]>();
        
        int level = 1;
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
        
        Random r = new Random();
        
        int randomIndex = r.nextInt(trainerPokemon.size());
        String randomKey = (String) trainerPokemon.keySet().toArray()[randomIndex];
        
        Object[] randomPokemon = trainerPokemon.get(randomKey);
        
        return randomPokemon;
    }
    
    
    public String battleMechanics(String pokemonName, String pokemonType, int pokemonLevel, int pokemonXP,
                                String trainerPokemonName, String trainerPokemonType, int trainerPokemonLevel) {
        System.out.println(pokemonName + ", Type: " + pokemonType + ", Level: " + pokemonLevel + "(XP: " + pokemonXP + ")");
        System.out.println("...VS...");
        System.out.println(trainerPokemonName + ", Type: " +  trainerPokemonType + ", Level: " + trainerPokemonLevel);
        
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
    
    public int pokemonLevelCalculator(int pokemonXP) {
        int calculatedLevel = 1;
        if (pokemonXP < 25000) {
            for (int i = 0; i < pokemonXPLevels.length; i++) {
                if (pokemonXPLevels[i] > pokemonXP) {
                    calculatedLevel = i;
                    break;
                }
            }
        }
        else {
            calculatedLevel = 5;
        }
        return calculatedLevel;
    }
    
    public void catchPokemon() {
        
    }
    
    
    public boolean endGame() {
        if (wonChampionship) {
            System.out.println("You beat the champion! You beat the game!");
            System.exit(0);
        }
        
        return true;
    }
    
    public void stop(String command) {
        if (command.equals("stop")) {
            System.out.println("You decided to quit.");
            System.exit(0);
        }
    }
    
    
}
