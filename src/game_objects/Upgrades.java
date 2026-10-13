package game_objects;
import utils.wrappers.Key;
import utils.math.Meth;
import utils.math.Pose;

public class Upgrades {
    public static double totalMoney = 100000;
    public static double moreMoney = 5;

    public static double upgradeHealthCost = 10;
    public static double healthIncrease = 10;
    public static double upgradeHealthCostIncrease = 10;

    public static double upgradeRocketDamageCost = 10;
    public static double beamDamage = 10;
    public static double rocketDamageIncrease = 10;
    public static double upgraderocketDamageCostIncrease = 10;

    public static double upgradeBeamSpeedCost = 10;
    public static double beamSpeed = 10;
    public static double beamSpeedIncrease = 10;
    public static double upgradeBeamSpeedCostIncrease = 10;

    public static void MoneyIncrease() {
        totalMoney = totalMoney + moreMoney;
    }

    public static void UpgradeHealth() {
        if(totalMoney >= upgradeHealthCost) {
            Rocketship.health = Rocketship.health + healthIncrease;
            totalMoney = totalMoney - upgradeHealthCost;
            upgradeHealthCost = upgradeHealthCost + upgradeHealthCostIncrease;
        }
        else {
            ;
        }
    }

    public static void UpgradeRocketDamage() {
        if(totalMoney >= upgradeRocketDamageCost) {
            Asteroids.rocketDamage = Asteroids.rocketDamage + rocketDamageIncrease;
            totalMoney = totalMoney - upgradeRocketDamageCost;
            upgradeRocketDamageCost = upgradeRocketDamageCost + upgraderocketDamageCostIncrease;
        }
        else {
            ;
        }
    }

    public static void UpgradeBeamSpeed() {
        if(totalMoney >= upgradeBeamSpeedCost) {
            beamSpeed = beamSpeed + beamSpeedIncrease;
            totalMoney = totalMoney - upgradeBeamSpeedCost;
            upgradeBeamSpeedCost = upgradeBeamSpeedCost + upgradeBeamSpeedCostIncrease;
        }
        else {
            ;
        }
    }
}
