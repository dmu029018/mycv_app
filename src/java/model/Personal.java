/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import core.Database;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.enums.Gender;

/**
 *
 * @author mati
 */
public class Personal 
{
    private String name, lastname, birthdate;
    private Gender gender;
    private List<Telephone> telephones = new ArrayList<>();
    private List<Email> emails = new ArrayList<>();
    private List<Picture> pictures = new ArrayList<>();
    private Location location;

    public Personal()
    {
        
    }
    
    public Personal(int userId)
    {
        
    }
    
    public static Personal findById(int id)
    {
        Personal personal = null;        
                
        try {
            ResultSet rs = Database.getInstance().query("select * from `personal` where `user_id`='" + id + "'");
            
            if(rs.next())
            {
                personal = new Personal();
                personal.setName(rs.getString("name"));
                personal.setLastname(rs.getString("lastname"));
                personal.setBirthdate(rs.getString("birthdate"));
                personal.setGender(Gender.findById(rs.getInt("gender_id")));
                personal.setLocation(Location.create(rs.getInt("country_id"), rs.getString("city")));
                
                
            }
        } 
        catch (Exception ex) 
        {
            System.err.println("Error de conexión con la base de datos.");
        }
        
        return personal;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<Telephone> telephones) {
        this.telephones = telephones;
    }

    public void addTelephone(Telephone telephone) {
        this.telephones.add(telephone);
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public void addEmail(Email email) {
        this.emails.add(email);
    }
    
    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
    
    public void addPicture(Picture picture) {
        this.pictures.add(picture);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    //DEJAR PARA EL FINAL!!!
    
}
