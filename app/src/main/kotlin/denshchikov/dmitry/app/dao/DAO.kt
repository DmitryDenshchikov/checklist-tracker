package denshchikov.dmitry.app.dao

interface DAO<T, K> {

    fun create(entity: K): K

    fun update(entity: K): Boolean

    fun get(id: T): K

    fun get(): List<K>

}