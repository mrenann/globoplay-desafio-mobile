package com.mrenann.globoplay.searchScreen.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrenann.globoplay.searchScreen.presentation.SearchEvent
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Mic

@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (SearchEvent) -> Unit,
    onSearch: (String) -> Unit
) {
    TextField(
        textStyle = TextStyle(fontSize = 16.sp),
        value = query,
        onValueChange = { newQuery ->
            onQueryChange(SearchEvent.EnteredQuery(newQuery))
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { Text("Search...") },
        singleLine = true,
        leadingIcon = {
            IconButton(onClick = { onSearch(query) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = { onSearch(query) }) {
                Icon(
                    imageVector = EvaIcons.Outline.Mic,
                    contentDescription = "Search Icon"
                )
            }
        },
        keyboardActions = KeyboardActions(
            onSearch = { onSearch(query) }
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = ImeAction.Search,
            autoCorrectEnabled = true,
            keyboardType = KeyboardType.Text
        ),
        colors = TextFieldColors(
            focusedContainerColor = Color.Black,
            unfocusedContainerColor = Color.DarkGray,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            disabledTextColor = Color.Gray,
            errorTextColor = Color.Red,
            disabledContainerColor = Color.DarkGray,
            cursorColor = Color.White,
            errorCursorColor = Color.Red,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedLeadingIconColor = Color.White,
            unfocusedLeadingIconColor = Color.White,
            disabledLeadingIconColor = Color.White,
            errorLeadingIconColor = Color.White,
            focusedTrailingIconColor = Color.White,
            unfocusedTrailingIconColor = Color.White,
            disabledTrailingIconColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            errorContainerColor = Color.DarkGray,
            textSelectionColors = TextSelectionColors(
                handleColor = Color.White,
                backgroundColor = Color.Black
            ),
            disabledIndicatorColor = Color.White,
            errorIndicatorColor = Color.White,
            errorTrailingIconColor = Color.White,
            disabledLabelColor = Color.White,
            errorLabelColor = Color.White,
            focusedPlaceholderColor = Color.White,
            unfocusedPlaceholderColor = Color.White,
            disabledPlaceholderColor = Color.White,
            errorPlaceholderColor = Color.White,
            focusedSupportingTextColor = Color.White,
            unfocusedSupportingTextColor = Color.White,
            disabledSupportingTextColor = Color.White,
            errorSupportingTextColor = Color.White,
            focusedPrefixColor = Color.White,
            unfocusedPrefixColor = Color.White,
            disabledPrefixColor = Color.White,
            errorPrefixColor = Color.White,
            focusedSuffixColor = Color.White,
            unfocusedSuffixColor = Color.White,
            disabledSuffixColor = Color.White,
            errorSuffixColor = Color.White,
        )
    )
}

@Preview
@Composable
fun SearchComponentPreview() {
    SearchComponent(query = "", onQueryChange = {}, onSearch = {})
}