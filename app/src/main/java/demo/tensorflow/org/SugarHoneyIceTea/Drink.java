package demo.tensorflow.org.SugarHoneyIceTea;

public class Drink {
    
    private String name;
    private double carbohydrates, sugar, protein, fat, salt, energy, saturates;
    public Drink(String name, double carbohydrates, double sugar, double protein,  double fat, double salt, double energy, double saturates){
        this.name = name;
        this.carbohydrates = carbohydrates;
        this.sugar = sugar;
        this.protein = protein;
        this.fat = fat;
        this.salt = salt;
        this.saturates = saturates;
        this.energy = energy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSalt() {
        return salt;
    }

    public void setSalt(double salt) {
        this.salt = salt;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getSaturates() {
        return saturates;
    }

    public void setSaturates(double saturates) {
        this.saturates = saturates;
    }

    /*Drink up7 = new Drink("7UP", 23.00 , 23.00 , 0 , 0 , 0.11 , 95 );
        Drink cocaCola = new Drink("CocaCola", 37.00 , 37.00, 0 , 0 , 0 , 149);
        Drink diet_Cola = new Drink ("Diet Cocacola", 0 , 0 , 0 , 0 , 0 , 1);
        Drink zero_Cola = new Drink("Cocacola Zero", 0 , 0 , 0 , 0 , 0 , 1);
        Drink fanta_FruitTwist = new Drink("Fanta Fruit Twist", 21.00, 21.00 , 0 , 0 , 0 , 86);
        Drink fanta_Orange = new Drink("Fanta Orange", 15.00, 15.00 , 0 , 0 , 0 , 63);
        Drink fanta_Lemon = new Drink("Fanta Lemon", 27.00 , 27.00 , 0 , 0 , 0 , 112);
        Drink drPepper = new Drink("Dr Pepper", 24.00 , 24.00 , 0 ,0, 0 , 96);
        Drink redBull = new Drink("Red-Bull", 27.50 , 27.50 , 0 , 0 , 0.2 , 115 );
        Drink redBull_SugarFree = new Drink ("Red-Bull Sugar Free" , 0 , 0 , 0 , 0 , 0.1 , 7.5);
        Drink boostEnergy = new Drink("Boost Energy", 27.20 , 26.40 , 0 , 0 , 0.19 , 118 );
        Drink fruitShoot_Apple = new Drink("Fruit Shoot Apple and Blackcurrant", 2.20 , 2.20 , 0 , 0 , 0.08, 14.00);
        Drink fuitShoot_Orange = new Drink("Fruit Shoot Orange",2.20 , 2.20 , 0 , 0 , 0.08, 14.00);
        Drink mountainDew = new Drink ("Mountain Dew " , 43.00 , 43.00 , 0 , 0 , 0.7 , 158);
        Drink Rio_Tropical = new Drink ("Rio Tropical" , 35.60 , 35.60 , 0 , 0 , 0 , 149);*/
}


