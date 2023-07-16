package game.enums;

/**
 * @author Chok Ming Jie & Soh Jin Huei
 * @version 1.0
 */



/**
 * Use this enum class to give `buff` or `debuff`.
 * It is also useful to give a `state` to abilities or actions that can be attached-detached.
 */
public enum Status {
    HOSTILE_TO_ENEMY, // use this capability to be hostile towards something (e.g., to be attacked by enemy)
    DEAD, // For all enemy
    RESET, //For Reset
    RAGE,
    FALL_TO_VALLEY, // For Valley Checking
    REST, // For Bonfire
    VALLEY_DIE, //FOR Valley die
    EXIST_TOKEN,
    DISARMED,
    STRIKING, // To show that the Storm Ruler is in Wind Slash mode
    SPINNING, // To show that the Giant Axe is in Spin Attack mode
    WEAK_TO_STORM_RULER,
    STUNNED,
    ACTIVATE,
    CHARGED,
    BOSS
}
