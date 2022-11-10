package com.team.restaurant_admin_panel.Utils;

import java.util.HashMap;

public final class Bundle{
    private Bundle(){
    }
    private static Bundle bundle = new Bundle();
    private HashMap<String,Object> map = new HashMap<>();
    public static Bundle getInstance(){
        return bundle;
    }
    public void put(String key, Object value){
        map.put(key, value);
    }
    public Object get(String key){
        return map.get(key);
    }
}
