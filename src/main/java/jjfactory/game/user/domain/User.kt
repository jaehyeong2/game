package jjfactory.game.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val username: String,
    val password: String,

    var level: Int = 1,
    var exp: Int = 0
) {

    fun levelUp() {
        if(exp == 100){
            level++
            exp = 0
        }
    }
}