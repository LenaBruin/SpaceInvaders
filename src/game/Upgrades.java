package game;
import game_objects.Asteroids;
import game_objects.Rocketship;

public class Upgrades {
    public static double totalMoney = 0;
    public static double moreMoney = 5;

    public static int score= 0;
    public static double upgradeHealthCost = 10;
    public static double healthIncrease = 10;
    public static double upgradeHealthCostIncrease = 10;

    public static double upgradeRocketDamageCost = 10;
    public static double beamDamage = 10;
    public static double rocketDamageIncrease = 10;
    public static double upgradeRocketDamageCostIncrease = 1.3;

    public static double upgradeBeamSpeedCost = 10;
    public static double beamSpeed = 10;
    public static double beamSpeedIncrease = 10;
    public static double upgradeBeamSpeedCostIncrease = 1.3;

    public static void moneyIncrease() {
        totalMoney = totalMoney + moreMoney;
    }

    public static void upgradeHealth() {
        if(totalMoney >= upgradeHealthCost) {
            Rocketship.health = Rocketship.health + healthIncrease;
            totalMoney = totalMoney - upgradeHealthCost;
            upgradeHealthCost = upgradeHealthCost + upgradeHealthCostIncrease;
        }
        else {
            ;
        }
    }

    public static void upgradeRocketDamage() {
        if(totalMoney >= upgradeRocketDamageCost) {
            Asteroids.rocketDamage = Asteroids.rocketDamage + rocketDamageIncrease;
            totalMoney = totalMoney - upgradeRocketDamageCost;
            upgradeRocketDamageCost = upgradeRocketDamageCost + upgradeRocketDamageCostIncrease;
        }
        else {
            ;
        }
    }

    public static void upgradeBeamSpeed() {
        if(totalMoney >= upgradeBeamSpeedCost) {
            beamSpeed = beamSpeed + beamSpeedIncrease;
            totalMoney = totalMoney - upgradeBeamSpeedCost;
            upgradeBeamSpeedCost = upgradeBeamSpeedCost * upgradeBeamSpeedCostIncrease;
        }
        else {
            ;
        }
    }
}
