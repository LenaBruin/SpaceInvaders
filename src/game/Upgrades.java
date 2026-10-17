package game;
import game_objects.PlasmaBeams;
import game_objects.Rocketship;

public class Upgrades {
    public static double totalMoney = 100;
    public static double moreMoney = 5;

    public static double upgradeHealthCost = 10;
    public static int healthIncrease = 10;
    public static double upgradeHealthCostIncrease = 1.3;

    public static double upgradeRocketDamageCost = 10;
    public static double rocketDamageIncrease = 10;
    public static double upgradeRocketDamageCostIncrease = 1.3;

    public static double upgradeBeamSpeedCost = 10;
    public static double beamSpeed = 10;
    public static double beamSpeedIncrease = 10;
    public static double upgradeBeamSpeedCostIncrease = 1.3;

    public static double moneyUpgradeGainCost = 10;
    public static double moneyGainIncrease= 2;
    public static double moneyUpgradeCostIncrease= 1.3;

    public static double spawnSpeedUpgradeCost= 10;
    public static double spawnSpeedIncrease= 1.3;
    public static double spawnSpeedUpgradeCostIncrease= 1.5;

    public static void moneyIncrease() {
        totalMoney = totalMoney + moreMoney;
    }

    public static void upgradeHealth() {
        if(totalMoney >= upgradeHealthCost) {
            Rocketship.maxHealth += healthIncrease;
            Rocketship.regenerationHealth*= 1.3;
            Rocketship.regenerationTime/=1.5;
            totalMoney -= upgradeHealthCost;
            upgradeHealthCost *= upgradeHealthCostIncrease;
        }
    }

    public static void upgradeRocketDamage() {
        if(totalMoney >= upgradeRocketDamageCost) {
            PlasmaBeams.damage+= rocketDamageIncrease;
            totalMoney -= upgradeRocketDamageCost;
            upgradeRocketDamageCost *= upgradeRocketDamageCostIncrease;
        }

    }

    public static void upgradeBeamSpeed() {
        if(totalMoney >= upgradeBeamSpeedCost) {
            beamSpeed += beamSpeedIncrease;
            totalMoney -= upgradeBeamSpeedCost;
            upgradeBeamSpeedCost *= upgradeBeamSpeedCostIncrease;
        }


    }
    public static void upgradeMoneyGain(){
        if(totalMoney >= moneyUpgradeGainCost){
            moreMoney*= moneyGainIncrease;
            totalMoney-= moneyUpgradeGainCost;
            moneyUpgradeGainCost *= moneyUpgradeCostIncrease;
        }
    }

    public static synchronized void upgradeSpawnSpeed(){
        if(totalMoney >= spawnSpeedUpgradeCost && PlasmaBeams.spawnSpeed> 20){
            PlasmaBeams.spawnSpeed/= spawnSpeedIncrease;
            totalMoney-= spawnSpeedUpgradeCost;
            spawnSpeedUpgradeCost *= spawnSpeedUpgradeCostIncrease;
        }
    }
}
