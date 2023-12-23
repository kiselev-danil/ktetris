package common

data class Coord(var x: Int = 0, var y: Int = 0) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this.javaClass != other.javaClass) return false
        other as Coord
        return (x == other.x && y == other.y)
    }
    operator fun plus(other: Coord): Coord {
        return Coord(x + other.x, y + other.y)
    }
    operator fun minus(other: Coord): Coord {
        return Coord(x - other.x, y - other.y)
    }
    operator fun times(scale: Int): Coord {
        return Coord(x * scale, y * scale)
    }

}