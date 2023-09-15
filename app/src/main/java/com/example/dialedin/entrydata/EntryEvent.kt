package com.example.dialedin.entrydata

sealed interface EntryEvent {
    object SaveEntry: EntryEvent
    data class SetTitle(val title: String): EntryEvent
    data class SetBean(val bean: String): EntryEvent
    data class SetGrindSize(val grindSize: Double): EntryEvent
    data class SetDose(val dose: Double): EntryEvent
    data class SetYield(val yield: Double): EntryEvent
    data class SetPreInfuse(val preInfuse: Double): EntryEvent
    data class SetTime(val time: Double): EntryEvent
    data class SetNotes(val notes: String): EntryEvent
    data class SetRating(val rating: Int): EntryEvent
    data class IsFavorite(val favorite: Boolean): EntryEvent
    object ShowDialog: EntryEvent
    object HideDialog: EntryEvent
    data class SortEntries(val sortType: SortType): EntryEvent
    data class DeleteEntry(val entry: Entry): EntryEvent
}