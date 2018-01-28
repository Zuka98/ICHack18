package demo.tensorflow.org.SugarHoneyIceTea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    static HashMap<Drink, Integer> totalHashMap = new HashMap<Drink, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent receivedIntent = getIntent();
        String drinkName = receivedIntent.getStringExtra("drinkUpdate");
        if(drinkName == null){
            addHashMapValues();
        }
        updateHashMapValue(drinkName);
        Button cameraButton = (Button) findViewById(R.id.camera_button);
        //Button voiceButton = (Button) findViewById(R.id.voice_button);
        Button totalButton = (Button) findViewById(R.id.total_button);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ClassifierActivity.class);
                startActivity(intent);
            }
        });

        putValues();

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
        fatText.setText(String.valueOf(totalFat));
        saturatesText.setText(String.valueOf(totalSaturate));
        carbText.setText(String.valueOf(totalCarbs));
        sugarText.setText(String.valueOf(totalSugar));
        proteinText.setText(String.valueOf(totalProtein));
        saltText.setText(String.valueOf(totalSalt));
    }
}
