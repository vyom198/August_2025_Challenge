package com.vs.august_2025_challenge.thermometerTrek

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ThermometerViewModel : ViewModel() {

    private var hasLoadedInitialData = false
    private val initialTempFlow = List(20) { null as Double? }
    private val _state = MutableStateFlow(ThermometerState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ThermometerState()
        )
    private fun getData (){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    tempFlow = initialTempFlow,
                    isCompleted = false
                )
            }
         temps.collect { temp->
           val filtered =   temp.filter {
               it != null && it >= 0
             }.take(20)
             filtered.forEachIndexed{ index,item ->
                 delay(250)
                 val fahernheit  = (item?.times(1.8))?.plus(32)
                 val formattedFahrenheit = String.format("%.1f",  fahernheit).toDouble()

                 _state.update {
                     val newList = _state.value.tempFlow.toMutableList()
                     newList[index] = formattedFahrenheit
                     it.copy(
                         tempFlow = newList,
                         size = _state.value.size + 1
                     )
                 }
             }

             _state.update {
                 it.copy(
                     isCompleted = true
                 )
             }

         }
        }
    }
    fun onAction(action: ThermometerAction) {
        when (action) {
            ThermometerAction.OnClickStart ->{
                _state.update {
                    it.copy(
                        isInitial = false
                    )
                }
                getData()
            }
            ThermometerAction.OnResetClick -> getData()
        }
    }

}