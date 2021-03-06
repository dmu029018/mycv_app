package model;

/**
 *
 * @author David
 */
public class Location 
{
    private Country country;
    private String city;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public static Location create(String country_id, String city)
    {
        return create(Integer.parseInt(country_id), city);
    }
    
    public static Location create(int country_id, String city)
    {
        Location location = new Location();
        location.setCountry(Country.findById(country_id));
        location.setCity(city);
        
        return location;
    }
}
