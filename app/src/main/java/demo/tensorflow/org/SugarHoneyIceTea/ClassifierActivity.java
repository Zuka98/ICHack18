/*
 * Copyright 2016 The TensorFlow Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo.tensorflow.org.SugarHoneyIceTea;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Typeface;

import android.media.ImageReader.OnImageAvailableListener;
import android.os.SystemClock;
import android.util.Size;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import demo.tensorflow.org.SugarHoneyIceTea.OverlayView.DrawCallback;
import demo.tensorflow.org.SugarHoneyIceTea.env.BorderedText;
import demo.tensorflow.org.SugarHoneyIceTea.env.Logger;

public class ClassifierActivity extends CameraActivity implements OnImageAvailableListener {
    private static final Logger LOGGER = new Logger();

    private static final Size DESIRED_PREVIEW_SIZE = new Size(640, 480);

    private Integer sensorOrientation;
    private MSCognitiveServicesClassifier classifier;

    private BorderedText borderedText;

    //Sidak Stuff
    static Drink up7 = new Drink("7-UP", 23.00 , 23.00 , 0 , 0 , 0.11 , 95, 0 );
    static Drink cocaCola = new Drink("Coca Cola", 37.00 , 37.00, 0 , 0 , 0 , 149, 0);
    static Drink dietCola = new Drink ("Coca Cola Light", 0 , 0 , 0 , 0 , 0 , 1, 0);
    static Drink zeroCola = new Drink("Coca Cola Zero", 0 , 0 , 0 , 0 , 0 , 1, 0);
    static Drink fantaFruitTwist = new Drink("Fanta FruitTwist", 21.00, 21.00 , 0 , 0 , 0 , 86, 0);
    static Drink fantaOrange = new Drink("Fanta Orange", 15.00, 15.00 , 0 , 0 , 0 , 63, 0);
    static Drink fantaLemon = new Drink("Fanta Lemon", 27.00 , 27.00 , 0 , 0 , 0 , 112, 0);
    static Drink drPepper = new Drink("Dr Pepper", 24.00 , 24.00 , 0 ,0, 0 , 96, 0);
    static Drink redBull = new Drink("RedBull", 27.50 , 27.50 , 0 , 0 , 0.2 , 115, 0);
    static Drink redBullSugarFree = new Drink ("RedBull Sugar Free" , 0 , 0 , 0 , 0 , 0.1 , 7.5, 0);
    static Drink boostEnergy = new Drink("Boost Energy", 27.20 , 26.40 , 0 , 0 , 0.19 , 118, 0);
    static Drink fruitShootApple = new Drink("Fruit Shoot Apple", 2.20 , 2.20 , 0 , 0 , 0.08, 14.00, 0);
    static Drink fruitShootOrange = new Drink("Fruit Shoot Orange",2.20 , 2.20 , 0 , 0 , 0.08, 14.00, 0);
    static Drink mountainDew = new Drink ("Mountain Dew" , 43.00 , 43.00 , 0 , 0 , 0.7 , 158, 0);
    static Drink rioTropical = new Drink ("Rio Tropical" , 35.60 , 35.60 , 0 , 0 , 0 , 149, 0);
    static Drink liptonIceTea = new Drink("Lipton Ice Tea", 24.00, 22.00, 1.00, 1.00, 0.32, 96.00, 0.20);
    static Drink springWater = new Drink("Camlica Water", 0, 0, 0, 0, 0, 0, 0);


    public static ArrayList<Drink> drinkArrayList = new ArrayList<Drink>(){
        {
            add(up7);
            add(cocaCola);
            add(dietCola);
            add(zeroCola);
            add(fantaFruitTwist);
            add(fantaLemon);
            add(fantaOrange);
            add(drPepper);
            add(redBull);
            add(redBullSugarFree);
            add(boostEnergy);
            add(fruitShootApple);
            add(fruitShootOrange);
            add(mountainDew);
            add(rioTropical);
            add(liptonIceTea);
            add(springWater);
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.camera_connection_fragment;
    }

    @Override
    protected Size getDesiredPreviewFrameSize() {
        return DESIRED_PREVIEW_SIZE;
    }

    private static final float TEXT_SIZE_DIP = 10;

    @Override
    public void onPreviewSizeChosen(final Size size, final int rotation) {
        final float textSizePx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, TEXT_SIZE_DIP, getResources().getDisplayMetrics());
        borderedText = new BorderedText(textSizePx);
        borderedText.setTypeface(Typeface.DEFAULT_BOLD);

        classifier = new MSCognitiveServicesClassifier(ClassifierActivity.this);

        previewWidth = size.getWidth();
        previewHeight = size.getHeight();

        final Display display = getWindowManager().getDefaultDisplay();
        final int screenOrientation = display.getRotation();

        LOGGER.i("Sensor orientation: %d, Screen orientation: %d", rotation, screenOrientation);

        sensorOrientation = rotation + screenOrientation;

        LOGGER.i("Initializing at size %dx%d", previewWidth, previewHeight);
        rgbFrameBitmap = Bitmap.createBitmap(previewWidth, previewHeight, Config.ARGB_8888);

        yuvBytes = new byte[3][];

        addCallback(
                new DrawCallback() {
                    @Override
                    public void drawCallback(final Canvas canvas) {
                        renderDebug(canvas);
                    }
                });
    }

    protected void processImageRGBbytes(final int[] rgbBytes) {
        rgbFrameBitmap.setPixels(rgbBytes, 0, previewWidth, 0, 0, previewWidth, previewHeight);

        runInBackground(
                new Runnable() {
                    @Override
                    public void run() {
                        final long startTime = SystemClock.uptimeMillis();
                        Classifier.Recognition r = classifier.classifyImage(rgbFrameBitmap, sensorOrientation);
                        lastProcessingTimeMs = SystemClock.uptimeMillis() - startTime;

                        final List<Classifier.Recognition> results = new ArrayList<>();

                        if (r.getConfidence() > 0.8) {
                            results.add(r);
                        }

                        LOGGER.i("Detect: %s", results);
                        if (resultsView == null) {
                            resultsView = findViewById(R.id.results);
                        }
                        resultsView.setResults(results);
                        requestRender();
                        if(!results.isEmpty()){
                            //String to search with : results.get(0).getTitle()
                            showDialogue(getDrinkByName(results.get(0).getTitle()));
                            LOGGER.d("Started Intent");
                            return;
                        }
                        computing = false;
                        if (postInferenceCallback != null) {
                            postInferenceCallback.run();
                        }
                    }
                });

    }

   private Drink getDrinkByName(String result){
        Drink answer =  new Drink("", 0, 0, 0, 0, 0, 0, 0);
        for(Drink d : drinkArrayList){
            if(d.getName().equals(result)){
                answer = d;
            }
        }
        return answer;
   }

    private void showDialogue(final Drink drink){
        //set up dialog
        final Dialog dialog = new Dialog(ClassifierActivity.this);
        dialog.setContentView(R.layout.activity_sample_dialogue);
        dialog.setCancelable(true);
        //there are a lot of settings, for dialog, check them all out!

        //set up text
        TextView nameText = (TextView) dialog.findViewById(R.id.item_name);
        TextView energyText = (TextView) dialog.findViewById(R.id.item_energy);
        TextView fatText = (TextView) dialog.findViewById(R.id.item_fat);
        TextView carbText = (TextView) dialog.findViewById(R.id.item_carb);
        TextView proteinText = (TextView) dialog.findViewById(R.id.item_protein);
        TextView saltText = (TextView) dialog.findViewById(R.id.item_salt);
        TextView saturatedText = (TextView) dialog.findViewById(R.id.item_saturates);
        TextView sugarText = (TextView) dialog.findViewById(R.id.item_sugars);

        nameText.setText(drink.getName());
        energyText.setText(String.valueOf(drink.getEnergy()));
        fatText.setText(String.valueOf(drink.getFat()));
        carbText.setText(String.valueOf(drink.getCarbohydrates()));
        proteinText.setText(String.valueOf(drink.getProtein()));
        saltText.setText(String.valueOf(drink.getSalt()));
        saturatedText.setText(String.valueOf(drink.getSaturates()));
        sugarText.setText(String.valueOf(drink.getSugar()));


        //set up button
        Button button = (Button) dialog.findViewById(R.id.Button01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                computing = false;
            }
        });

        Button button1 = (Button) dialog.findViewById(R.id.Button02);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                Intent i = new Intent(view.getContext(), MainActivity.class);
                i.putExtra("drinkUpdate", drink.getName());
                startActivity(i);
            }
        });
        //now that the dialog is set up, it's time to show it
        dialog.show();
    }

    @Override
    public void onSetDebug(boolean debug) {
    }

    private void renderDebug(final Canvas canvas) {
        if (!isDebug()) {
            return;
        }

        final Vector<String> lines = new Vector<String>();
        lines.add("Inference time: " + lastProcessingTimeMs + "ms");
        borderedText.drawLines(canvas, 10, canvas.getHeight() - 10, lines);
    }
}
