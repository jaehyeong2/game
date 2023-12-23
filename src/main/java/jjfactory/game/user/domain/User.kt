package jjfactory.game.user.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Table(name = "users")
@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val username: String,
    val password: String,

    var level: Int = 1,
    var exp: Int = 0,

    var point: Long = 0,

    // 반정규화
    var clearStageLevel: Int = 0
) {

    fun levelUp() {
        if(exp == 100){
            level++
            exp = 0
        }
    }

    fun addPoint(num: Int){
        point += num
    }
}