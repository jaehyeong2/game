package jjfactory.game.util

import org.slf4j.LoggerFactory

class LoggerUtil{

}

inline fun <reified T> T.logger() = LoggerFactory.getLogger(T::class.java)

