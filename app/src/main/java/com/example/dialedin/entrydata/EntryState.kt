package com.example.dialedin.entrydata

data class EntryState(
    val entries: List<Entry> = emptyList(),
    val title: String = "",
    val bean: String = "",
    val grindSize: Double = 0.0,
    val dose: Double = 0.0,
    val yield: Double = 0.0,
    val preInfuse: Double = 0.0,
    val time: Double = 0.0,
    val notes: String = "",
    val rating: Int = 5,
    val favorite: Boolean = false,

    val isAddingEntry: Boolean = false,
    val sortType: SortType = SortType.RATING
)
