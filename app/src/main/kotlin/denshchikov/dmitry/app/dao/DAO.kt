package denshchikov.dmitry.app.dao

interface DAO<T, K> {

    fun create(task: K): K

    fun update(task: K): Boolean

    fun get(id: T): K

    fun get(): List<K>

}