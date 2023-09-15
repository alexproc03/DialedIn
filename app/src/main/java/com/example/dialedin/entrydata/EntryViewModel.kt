package com.example.dialedin.entrydata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EntryViewModel(private val dao: EntryDao) : ViewModel() {

    private val _sortType = MutableStateFlow(SortType.RATING)
    private val _entries = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.RATING -> dao.getEntriesOrderedByRating()
                SortType.TITLE -> dao.getEntriesOrderedByTitle()
                SortType.BEAN -> dao.getEntriesOrderedByBeans()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(EntryState())
    val state = combine(_state, _sortType, _entries) { state, sortType, entries ->
        state.copy(
            entries = entries,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), EntryState())

    fun onEvent(event: EntryEvent) {
        when(event) {

            EntryEvent.SaveEntry -> {
                val title = state.value.title
                val bean = state.value.bean
                val grindSize = state.value.grindSize
                val dose = state.value.dose
                val yield = state.value.yield
                val preInfuse = state.value.preInfuse
                val time = state.value.time
                val notes = state.value.notes
                val rating = state.value.rating
                val favorite = state.value.favorite

                val entry = Entry(
                    title = title,
                    bean = bean,
                    grindSize = grindSize,
                    dose = dose, yield = yield,
                    preInfuse = preInfuse,
                    time = time,
                    notes = notes,
                    rating = rating,
                    favorite = favorite
                )
                viewModelScope.launch {
                    dao.upsertEntry(entry)
                }
                _state.update { it.copy(
                    isAddingEntry = false,
                    title = "",
                    bean = "",
                    grindSize = 0.0,
                    dose = 0.0,
                    yield = 0.0,
                    preInfuse = 0.0,
                    time = 0.0,
                    notes = "",
                    rating = 5,
                    favorite = false
                ) }
            }

            is EntryEvent.DeleteEntry -> {
                viewModelScope.launch{
                    dao.deleteEntry(event.entry)
                }
            }
            EntryEvent.HideDialog -> {
                _state.update {it.copy(
                    isAddingEntry = false
                )}
            }
            EntryEvent.ShowDialog -> {
                _state.update {it.copy(
                    isAddingEntry = true
                )}
            }
            is EntryEvent.IsFavorite -> {
                _state.update { it.copy(
                    favorite = event.favorite
                )}
            }
            is EntryEvent.SetBean ->  {
                _state.update { it.copy(
                    bean = event.bean
                )}
            }
            is EntryEvent.SetDose ->  {
                _state.update { it.copy(
                    dose = event.dose
                )}
            }
            is EntryEvent.SetGrindSize ->  {
                _state.update { it.copy(
                    grindSize = event.grindSize
                )}
            }
            is EntryEvent.SetNotes ->  {
                _state.update { it.copy(
                    notes = event.notes
                )}
            }
            is EntryEvent.SetPreInfuse ->  {
                _state.update { it.copy(
                    preInfuse = event.preInfuse
                )}
            }
            is EntryEvent.SetRating ->  {
                _state.update { it.copy(
                    rating = event.rating
                )}
            }
            is EntryEvent.SetTime ->  {
                _state.update { it.copy(
                    time = event.time
                )}
            }
            is EntryEvent.SetTitle ->  {
                _state.update { it.copy(
                    title = event.title
                )}
            }
            is EntryEvent.SetYield ->  {
                _state.update { it.copy(
                    yield = event.yield
                )}
            }
            is EntryEvent.SortEntries -> {
                _sortType.value = event.sortType
            }
        }
    }
}