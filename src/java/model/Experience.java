/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import helpers.DatabaseUtils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mati
 */
public class Experience 
{
    private int id, hours;
    private String job, enterprise, startdate, enddate, description;
    private Sector sector;
    private Location location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    public static Experience findById(int id) 
    {
        Experience experience = null;

        try {
            
            ResultSet rs = DatabaseUtils.selectById("experience", id);
            
            if (rs.next()) 
            {
                experience = instantiateFromCurrentResult(rs);
            }
        } 
        catch (Exception ex) 
        {
            System.err.println("Error de conexión con la base de datos.");
        }

        return experience;
    }

    public static List<Experience> findAll() 
    {
        List<Experience> list = new ArrayList<>();
        
        try 
        {
            ResultSet rs = DatabaseUtils.selectAll("experience");
            
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

    public static List<Experience> findBy(String attr, String value) 
    {
        List<Experience> list = new ArrayList<>();
        
        try {
            ResultSet rs = DatabaseUtils.selectAllWhere("experience", attr, value);
            
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

    public static Experience instantiateFromCurrentResult(ResultSet rs) throws SQLException
    {
        Experience experience = new Experience();
        experience.setEnterprise(rs.getString("enterprise"));
        experience.setDescription(rs.getString("description"));
        experience.setStartdate(rs.getString("startdate"));
        experience.setEnddate(rs.getString("enddate"));
        experience.setLocation(Location.create(rs.getInt("country_id"), rs.getString("city")));
        experience.setSector(Sector.findById(rs.getInt("sector_id")));
        experience.setHours(rs.getInt("hours"));
        experience.setJob(rs.getString("job"));
        return experience;
    }
    
    
    
}