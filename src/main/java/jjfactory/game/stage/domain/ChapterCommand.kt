package jjfactory.game.stage.domain

class ChapterCommand {
    data class Create(
        val name: String,
        val description: String,
        val ordering: Int,
    ) {
        fun toEntity(): Chapter {
            return Chapter(
                ordering = ordering,
                name = name,
                description = description
            )
        }
    }

    data class Update(
        val name: String,
        val description: String,
        val ordering: Int,
    )
}