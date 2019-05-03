package example

import junit.framework.Assert.*
import org.junit.Test

class CharactersShould {

    val player = Player(PlayerType.MELEE)

    @Test
    fun `start at 1000 health`(){
        assertEquals(1000,player.health)
    }

    @Test
    fun `be dead after they die`(){
        player.kill()
        assertFalse(player.alive())
    }

    @Test
    fun `start alive`() {
        assertTrue(player.alive())
    }

    @Test
    fun `heal increment your health by a number`() {
        player.health = 100
        player.heal(100)
        assertEquals(200, player.health)
    }

    @Test
    fun `not heal above 1000 health`() {
        player.health = 700
        player.heal(301)
        assertEquals(1000, player.health)
    }

    @Test
    fun `not be healed when is dead`() {
        player.kill()
        player.heal(100)
        assertEquals(0, player.health)
    }

    @Test
    fun `take damage`() {
        player.takeDamage(1001)
        assertEquals(0, player.health)
        assertTrue(!player.alive())
    }

    @Test
    fun `damage other players`(){
        val player2 = Player(PlayerType.MELEE)
        player.damage(player2,100, 1)
        assertEquals(900,player2.health)
    }

    @Test
    fun `not damage themselves`() {
        player.damage(player, 100, 1)
        assertEquals(1000, player.health)
    }

    @Test
    fun `damage 50% more if target is 5 or more levels below`(){
        val weakPlayer = Player(PlayerType.MELEE)
        player.level = 6
        player.damage(weakPlayer,100, 1)
        assertEquals(850, weakPlayer.health)
    }

    @Test
    fun `damage 50% less if target is 5 or more levels above`(){
        val weakPlayer = Player(PlayerType.MELEE)
        player.level = 6
        weakPlayer.damage(player,100, 1)
        assertEquals(950, player.health)
    }

    @Test
    fun `have an attack range of 2 meters if he is a melee fighter`() {
        val meleePlayer = Player(PlayerType.MELEE)
        assertEquals(2, meleePlayer.range)
    }

    @Test
    fun `have an attack range of 20 meters if he is a ranged fighter`(){
        val rangedPlayer = Player(PlayerType.RANGED)
        assertEquals(20,rangedPlayer.range)
    }

    @Test
    fun `can only attack players in range`(){
        val otherPlayer = Player(PlayerType.RANGED)
        player.damage(otherPlayer,100, 4)
        assertEquals(1000,otherPlayer.health)
    }
}