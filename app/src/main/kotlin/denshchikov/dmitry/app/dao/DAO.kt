package denshchikov.dmitry.app.dao

interface DAO<T, K> {

    fun create(task: T): K

}