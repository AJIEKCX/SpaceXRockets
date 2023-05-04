package ru.alexpanov.rockets.api.data

import ru.alexpanov.core.cache.Cache
import ru.alexpanov.core.cache.MemoryCache
import ru.alexpanov.rockets.internal.domain.model.Rocket

class RocketsMemoryCache : Cache<List<Rocket>> by MemoryCache()