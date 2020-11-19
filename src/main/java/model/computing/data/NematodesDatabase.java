package model.computing.data;

import org.apache.commons.lang3.math.NumberUtils;

public class NematodesDatabase extends Creatures {

    private double cpValue;
    private double weight;
    private String foodShort;
    private String guild;

    //Constructor
    public NematodesDatabase(String name, String guild, double weight) {
        super(name);
        this.guild = guild;
        this.weight = weight;
        this.foodShort = getShortGuild(guild);
        this.cpValue = getCPValue(guild);

    }

    //getters
    private String getShortGuild(String guild) {
        return Character.toString(guild.charAt(0));
    }

    private Double getCPValue(String guild) {
        String cp = guild.substring(1);
        if (NumberUtils.isCreatable(cp))
            return Double.parseDouble(guild.substring(1));
        return 0.0;
    }

    public double getCp() {
        return cpValue;
    }

    public double getWeight() {
        return weight;
    }

    public String getFoodShort() {
        return foodShort;
    }

    public String getGuild() {
        return guild;
    }

}
