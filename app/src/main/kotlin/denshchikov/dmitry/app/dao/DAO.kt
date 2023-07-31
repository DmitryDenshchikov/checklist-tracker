package denshchikov.dmitry.app.dao

interface DAO<T> {

    fun create(task: T)

}