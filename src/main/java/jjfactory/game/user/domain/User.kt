package jjfactory.game.user.domain

import jakarta.persistence.*

@Table(name = "users")
@Entity
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val username: String,
    val password: String,

    var level: Int = 1,
    var exp: Int = 0,

    var money: Long = 0,

    // 반정규화
    var clearStageLevel: Int = 0
) {

    fun levelUp() {
        if (exp == 100) {
            level++
            exp = 0
        }
    }

    fun increaseMoney(num: Int) {
        money += num
    }

    fun decreaseMoney(num: Int) {
        if (money < num) throw RuntimeException("부족")
        money -= num
    }
}