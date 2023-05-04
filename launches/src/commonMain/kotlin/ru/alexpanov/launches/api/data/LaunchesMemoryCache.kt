package ru.alexpanov.launches.api.data

import ru.alexpanov.core.cache.Cache
import ru.alexpanov.core.cache.MemoryCache
import ru.alexpanov.launches.internal.domain.model.RocketLaunch

class LaunchesMemoryCache : Cache<List<RocketLaunch>> by MemoryCache()