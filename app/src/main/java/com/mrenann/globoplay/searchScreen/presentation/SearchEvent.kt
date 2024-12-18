package com.mrenann.globoplay.searchScreen.presentation

sealed class SearchEvent {
    data class EnteredQuery(val query: String) : SearchEvent()

}