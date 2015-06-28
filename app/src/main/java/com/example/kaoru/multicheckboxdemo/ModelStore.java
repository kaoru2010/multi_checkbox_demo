package com.example.kaoru.multicheckboxdemo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kaoru on 2015/06/28.
 */
public class ModelStore {
    private static final Map<ModelKeyInterface, Model> sModels = new HashMap<>();

    public static Model registerModel(ModelKeyInterface modelKey, Model model) {
        return sModels.put(modelKey, model);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Model> T get(ModelKeyInterface modelKey) {
        return (T) sModels.get(modelKey);
    }
}
