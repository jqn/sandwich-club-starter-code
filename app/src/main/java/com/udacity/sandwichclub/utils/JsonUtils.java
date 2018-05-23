package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import java.util.ArrayList;

public class JsonUtils {
    private static final String TAG = "JsonUtils";

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            JSONObject object = new JSONObject(json);

            sandwich.setMainName(object.getJSONObject("name").getString("mainName"));

            ArrayList<String> listKnownAs = new ArrayList<String>();

            JSONArray arrayKnownAs = object.getJSONObject("name").getJSONArray("alsoKnownAs");

            if (arrayKnownAs != null) {
                for(int i = 0; i < arrayKnownAs.length(); i++){
                    Log.d(TAG, "item " + arrayKnownAs.getString(i));
                    listKnownAs.add(arrayKnownAs.getString(i));
                }
            }

            sandwich.setAlsoKnownAs(listKnownAs);

            sandwich.setPlaceOfOrigin(object.getString("placeOfOrigin"));

            sandwich.setDescription(object.getString("description"));

            sandwich.setImage(object.getString("image"));

            ArrayList<String> ingredientsList = new ArrayList<String>();

            JSONArray arrayIngredients = object.getJSONArray("ingredients");
            for(int i =0; i < arrayIngredients.length(); i++) {
                ingredientsList.add((arrayIngredients.getString(i)));
            }

            sandwich.setIngredients(ingredientsList);

        } catch (JSONException e) {
            System.out.println("Failed to parse json");
        }
        return sandwich;
    }
}
