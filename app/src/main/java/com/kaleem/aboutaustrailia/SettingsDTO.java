package com.kaleem.aboutaustrailia;

/**
 * Created by Samad Ali on 1/28/2018.
 */

public class SettingsDTO {
    //private variables
    public int _id;
    public String _name;
    public String _pass;
    // Empty constructor
    public SettingsDTO(){

    }
    // constructor
    public SettingsDTO(int id){
        this._id = id;
    }
    // constructor
    public SettingsDTO(int id, String nm, String pass){
        this._id = id;
        this._name= nm;
        this._pass = pass;
    }

    // constructor
    public SettingsDTO(String nm, String pass){
        this._name= nm;
        this._pass = pass;
    }
    // getting ID

}
