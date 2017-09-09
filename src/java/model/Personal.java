/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import config.Config;
import core.Database;
import helpers.DatabaseUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author mati
 */
public class Personal 
{
    private int user_id;
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
            ResultSet rs = DatabaseUtils.selectAllWhere("personal", "user_id", id);
            
            if(rs.next())
            {
                personal = instantiateFromCurrentResult(rs);
            }
        } 
        catch (Exception ex) 
        {
            System.err.println("Error de conexión con la base de datos.");
        }
        
        return personal;
    }
    
    
    public static List<Personal> findBy(String attr, String value)
    {
        List<Personal> list = new ArrayList<>();        
                
        try {
            ResultSet rs = DatabaseUtils.selectAllWhere("personal", attr, value);
            
            while(rs.next())
            {
                list.add(instantiateFromCurrentResult(rs));
            }
        } 
        catch (Exception ex)
        {
            System.err.println("Error de conexión con la base de datos.");
        }
        
        return list;
    }
    
    public static Personal instantiateFromCurrentResult(ResultSet rs) throws SQLException
    {
        Personal personal = new Personal();
        personal.setName(rs.getString("name"));
        personal.setLastname(rs.getString("lastname"));
        personal.setBirthdate(rs.getString("birthdate"));
        personal.setGender(Gender.findById(rs.getInt("gender_id")));
        personal.setLocation(Location.create(rs.getInt("country_id"), rs.getString("city")));
        return personal;       
    }
    
    public boolean exists()
    {
        return !findBy("user_id", String.valueOf(this.getUser_id())).isEmpty();
    }
    
    public boolean insert() throws Exception
    {
        int affected;
        String query;
        
        try{
            if(this.exists())
            {
                query = String.format("update `personal` set `name`='%s', `lastname`='%s', `birthdate`='%s', `gender_id`='%d' `country_id`='%d', `city`='%s' where `user_id`=%d';", this.getName(), this.getLastname(), this.getBirthdate(), this.getGender().getId(), this.getLocation().getCountry().getId(), this.getLocation().getCity(), this.getUser_id());
            }
            else
            {
                query = String.format("insert into `personal`(`user_id`, `name`, `lastname`, `birthdate`, `gender_id`, `country_id`, `city`) values (%d, '%s','%s','%s', %d, %d,'%s')", this.getUser_id(), this.getName(), this.getLastname(), this.getBirthdate(), this.getGender().getId(), this.getLocation().getCountry().getId(), this.getLocation().getCity());
                
            }
            System.out.println("Consulta: "+query);
            affected = Database.getInstance().queryUpdate(query); 
            
        }
        catch(Exception ex)
        {
            System.err.println("Error al insertar/modificar info personal");
            affected = 0;
        }
        return affected > 0;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.user_id;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.lastname);
        hash = 41 * hash + Objects.hashCode(this.birthdate);
        hash = 41 * hash + Objects.hashCode(this.gender);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Personal other = (Personal) obj;
        if (this.user_id != other.user_id) {
            return false;
        }
        return true;
    }
    

    
}
