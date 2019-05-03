package example

class Player(type: PlayerType) {
    var health = 1000
    var level = 1
    var alive = true
    var range = type.range

    fun kill() {
        alive = false
        health = 0
    }

    fun alive(): Boolean {
       return alive
    }

    fun heal(newHealth: Int) {
        if (alive()) {
            health = Math.min(health + newHealth, 1000)
        }
    }

    fun damage(player: Player, damageTaken: Int, distanceToPlayer: Int) {
        if (player !== this) {
            if (distanceToPlayer <= this.range) {
                var boostDamage = 1.0
                if(player.level <= level - 5){
                    boostDamage = 1.5
                } else if (player.level >= level + 5) {
                    boostDamage = 0.5
                }
                player.takeDamage((damageTaken * boostDamage).toInt())
            }
        }
    }

    fun takeDamage(damage: Int) {
        health -= damage

        if(health <= 0){
            alive = false
            health = 0
        }
    }
}