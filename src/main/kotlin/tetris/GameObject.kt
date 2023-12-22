package tetris

import tetris.component.GameProperty


class GameObject(properties: Map<String, GameProperty>?) {
    var props: MutableMap<String, GameProperty>

    init {
        if (properties != null) {
            props = properties.toMutableMap()
        } else {
            props = HashMap()
        }
    }


    fun addProperty(name: String, property: GameProperty) {
        props[name] = property
    }

    fun removeProperty(type: PropertyType, name: String?) {
        if (name.isNullOrBlank()) {
            props.keys.forEach { b ->
                if (props.get(b)?.getType()?.equals(type) ?: false) {
                    props.minus(b)
                }
            }
        } else {
            props.minus(name)
        }
    }

}