package ru.alexpanov.core.cache

class MemoryCache<T> : Cache<T> {
    private val data = HashMap<Any, T>()

    override operator fun get(key: Any): T? {
        return data[key]
    }

    override operator fun set(key: Any, value: T) {
        data[key] = value
    }

    fun clear() {
        data.clear()
    }
}

interface Cache<T> {
    operator fun get(key: Any = Unit): T?
    operator fun set(key: Any = Unit, value: T)
}