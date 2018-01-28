package demo.tensorflow.org.SugarHoneyIceTea;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    static HashMap<Drink, Integer> totalHashMap = new HashMap<Drink, Integer>();
    final double MAX_ENERGY = 2080;
    final double MAX_FAT = 70;
    final double MAX_SATURATED = 24;
    final double MAX_CARBS = 310;
    final double MAX_SUGAR = 90;
    final double MAX_PROTEINS = 50;
    final double MAX_SALT = 2.3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent receivedIntent = getIntent();
        String drinkName = receivedIntent.getStringExtra("drinkUpdate");
        if(drinkName == null){
            addHashMapValues();
        } else if (drinkName.equals("reset")){
            addHashMapValues();
        }
        updateHashMapValue(drinkName);
        Button cameraButton = (Button) findViewById(R.id.camera_button);
        Button resetButton = (Button) findViewById(R.id.reset_button);
        Button totalButton = (Button) findViewById(R.id.total_button);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ClassifierActivity.class);
                startActivity(intent);
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                intent.putExtra("drinkUpdate", "reset");
                startActivity(intent);
            }
        });

        totalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogue();
            }
        });

        putValues();

    }
    private void showDialogue(){
        //set up dialog
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.activity_list_dialogue);
        dialog.setCancelable(true);

        //set up text
        TextView up7Text = (TextView) dialog.findViewById(R.id.up7_text);
        TextView cocaColaText = (TextView) dialog.findViewById(R.id.cocacola_text);
        TextView dietColaText = (TextView) dialog.findViewById(R.id.dietCola_text);
        TextView zeroColaText = (TextView) dialog.findViewById(R.id.zeroCola_text);
        TextView fantaFruitText = (TextView) dialog.findViewById(R.id.fantaFruitTwist_text);
        TextView fantaOrangeText = (TextView) dialog.findViewById(R.id.fantaOrange_text);
        TextView fantaLemonText = (TextView) dialog.findViewById(R.id.fantaLemon_text);
        TextView drPepperText = (TextView) dialog.findViewById(R.id.drPepper_text);
        TextView redBullText = (TextView) dialog.findViewById(R.id.redBull_text);
        TextView redBullSugarFreeText = (TextView) dialog.findViewById(R.id.redBullSugarFree_text);
        TextView boostText = (TextView) dialog.findViewById(R.id.boost_text);
        TextView fruitOrangeText = (TextView) dialog.findViewById(R.id.fruitOrange_text);
        TextView fruitAppleText = (TextView) dialog.findViewById(R.id.fruitApple_text);
        TextView mountainDewText = (TextView) dialog.findViewById(R.id.mountainDew_text);
        TextView rioText = (TextView) dialog.findViewById(R.id.rio_text);
        TextView liptonText = (TextView) dialog.findViewById(R.id.lipton_text);
        TextView waterText = (TextView) dialog.findViewById(R.id.water_text);


        //set up LinearLayouts
        LinearLayout up7view = (LinearLayout) dialog.findViewById(R.id.up7_view);
        up7view.setVisibility(View.INVISIBLE);
        LinearLayout cocaColaview = (LinearLayout) dialog.findViewById(R.id.cocacola_view);
        cocaColaview.setVisibility(View.INVISIBLE);
        LinearLayout dietColaview = (LinearLayout) dialog.findViewById(R.id.dietCola_view);
        dietColaview.setVisibility(View.INVISIBLE);
        LinearLayout zeroColaview = (LinearLayout) dialog.findViewById(R.id.zeroCola_view);
        zeroColaview.setVisibility(View.INVISIBLE);
        LinearLayout fantaFruitview = (LinearLayout) dialog.findViewById(R.id.fantaFruitTwist_view);
        fantaFruitview.setVisibility(View.INVISIBLE);
        LinearLayout fantaOrangeview = (LinearLayout) dialog.findViewById(R.id.fantaOrange_view);
        fantaOrangeview.setVisibility(View.INVISIBLE);
        LinearLayout fantaLemonview = (LinearLayout) dialog.findViewById(R.id.fantaLemon_view);
        fantaLemonview.setVisibility(View.INVISIBLE);
        LinearLayout drPepperview = (LinearLayout) dialog.findViewById(R.id.drPepper_view);
        drPepperview.setVisibility(View.INVISIBLE);
        LinearLayout redBullview = (LinearLayout) dialog.findViewById(R.id.redBull_view);
        redBullview.setVisibility(View.INVISIBLE);
        LinearLayout redBullSugarFreeview = (LinearLayout) dialog.findViewById(R.id.redBullSugarFree_view);
        redBullSugarFreeview.setVisibility(View.INVISIBLE);
        LinearLayout boostview = (LinearLayout) dialog.findViewById(R.id.boost_view);
        boostview.setVisibility(View.INVISIBLE);
        LinearLayout fruitOrangeview = (LinearLayout) dialog.findViewById(R.id.fruitOrange_view);
        fruitOrangeview.setVisibility(View.INVISIBLE);
        LinearLayout fruitAppleview = (LinearLayout) dialog.findViewById(R.id.fruitApple_view);
        fruitAppleview.setVisibility(View.INVISIBLE);
        LinearLayout mountainDewview = (LinearLayout) dialog.findViewById(R.id.mountainDew_view);
        mountainDewview.setVisibility(View.INVISIBLE);
        LinearLayout rioview = (LinearLayout) dialog.findViewById(R.id.rio_view);
        rioview.setVisibility(View.INVISIBLE);
        LinearLayout liptonview = (LinearLayout) dialog.findViewById(R.id.lipton_view);
        liptonview.setVisibility(View.INVISIBLE);
        LinearLayout waterview = (LinearLayout) dialog.findViewById(R.id.water_view);
        waterview.setVisibility(View.INVISIBLE);


        for (HashMap.Entry<Drink, Integer> entry : totalHashMap.entrySet()) {
            Drink drink = entry.getKey();
            Integer amount = entry.getValue();
            if(amount > 0){
                if(drink.getName().equals("7-UP")){
                    up7Text.setText(String.valueOf(amount));
                    up7view.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("Boost Energy")){
                    boostText.setText(String.valueOf(amount));
                    boostview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("Coca Cola")){
                    cocaColaText.setText(String.valueOf(amount));
                    cocaColaview.setVisibility(View.VISIBLE);

                }
                if(drink.getName().equals("Coca Cola Zero")){
                    zeroColaText.setText(String.valueOf(amount));
                    zeroColaview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("Coca Cola Light")){
                    dietColaText.setText(String.valueOf(amount));
                    dietColaview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("Dr Pepper")){
                    drPepperText.setText(String.valueOf(amount));
                    drPepperview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("Fanta FruitTwist")){
                    fantaFruitText.setText(String.valueOf(amount));
                    fantaFruitview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("Fanta Lemon")){
                    fantaLemonText.setText(String.valueOf(amount));
                    fantaLemonview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("Fanta Orange")){
                    fantaOrangeText.setText(String.valueOf(amount));
                    fantaOrangeview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("Fruit Shoot Apple")){
                    fruitAppleText.setText(String.valueOf(amount));
                    fruitAppleview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("Fruit Shoot Orange")){
                    fruitOrangeText.setText(String.valueOf(amount));
                    fruitOrangeview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("Lipton Ice Tea")){
                    liptonText.setText(String.valueOf(amount));
                    liptonview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("Mountain Dew")){
                    mountainDewText.setText(String.valueOf(amount));
                    mountainDewview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("RedBull")){
                    redBullText.setText(String.valueOf(amount));
                    redBullview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("RedBull Sugar Free")){
                    redBullSugarFreeText.setText(String.valueOf(amount));
                    redBullSugarFreeview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("Rio Tropical")){
                    rioText.setText(String.valueOf(amount));
                    rioview.setVisibility(View.VISIBLE);
                }
                if(drink.getName().equals("Camlica Water")){
                    waterText.setText(String.valueOf(amount));
                    waterview.setVisibility(View.VISIBLE);
                }
            }
        }

        //now that the dialog is set up, it's time to show it
        dialog.show();
    }

    private void addHashMapValues(){
        for(Drink drink: ClassifierActivity.drinkArrayList)
        {
            totalHashMap.put(drink, 0);
        }
    }

    private void updateHashMapValue(String drinkName){
        Drink drinkKey = new Drink("", 0, 0, 0, 0, 0, 0, 0);
        int newValue = 0;
        for (HashMap.Entry<Drink, Integer> entry : totalHashMap.entrySet()) {
            Drink drink = entry.getKey();
            Integer amount = entry.getValue();
            if(drink.getName().equals(drinkName)){
                drinkKey = drink;
                newValue = amount + 1;
            }
        }
        totalHashMap.put(drinkKey, newValue);
    }

    private void putValues(){
        double totalEnergy = 0;
        double totalFat = 0;
        double totalSaturate = 0;
        double totalCarbs = 0;
        double totalSugar = 0;
        double totalProtein = 0;
        double totalSalt = 0;

        TextView energyText = (TextView) findViewById(R.id.energy_text);
        TextView fatText = (TextView) findViewById(R.id.fat_text);
        TextView saturatesText = (TextView) findViewById(R.id.saturates_text);
        TextView carbText = (TextView) findViewById(R.id.carb_text);
        TextView sugarText = (TextView) findViewById(R.id.sugars_text);
        TextView proteinText = (TextView) findViewById(R.id.protein_text);
        TextView saltText = (TextView) findViewById(R.id.salt_text);

        for (HashMap.Entry<Drink, Integer> entry : totalHashMap.entrySet()) {
            Drink drink = entry.getKey();
            Integer amount = entry.getValue();
            totalEnergy += drink.getEnergy() * amount;
            totalFat += drink.getFat() * amount;
            totalSaturate += drink.getSaturates() * amount;
            totalCarbs += drink.getCarbohydrates() * amount;
            totalSugar += drink.getSugar() * amount;
            totalProtein += drink.getProtein() * amount;
            totalSalt += drink.getSalt() * amount;
        }

        energyText.setText(String.valueOf(totalEnergy));
        if(totalEnergy >= MAX_ENERGY){
            energyText.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        fatText.setText(String.valueOf(totalFat));
        if(totalFat >= MAX_FAT){
            fatText.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        saturatesText.setText(String.valueOf(totalSaturate));
        if(totalSaturate >= MAX_SATURATED){
            saturatesText.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        carbText.setText(String.valueOf(totalCarbs));
        if(totalCarbs >= MAX_CARBS){
            carbText.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        sugarText.setText(String.valueOf(totalSugar));
        if(totalSugar >= MAX_SUGAR){
            sugarText.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        proteinText.setText(String.valueOf(totalProtein));
        if(totalProtein >= MAX_PROTEINS){
            proteinText.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        saltText.setText(String.valueOf(totalSalt));
        if(totalSalt >= MAX_SALT){
            saltText.setTextColor(getResources().getColor(R.color.colorAccent));
        }
    }
}
