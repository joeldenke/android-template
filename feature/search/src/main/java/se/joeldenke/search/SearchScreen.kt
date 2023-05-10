package se.joeldenke.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import se.joeldenke.theme.ui.component.JSearch
import se.joeldenke.theme.ui.component.JText
import se.joeldenke.theme.ui.component.JTextResource
import se.joeldenke.theme.ui.theme.JDesignSystem

data class SearchUiState(
    val query: String = "",
)

class SearchViewModel : ViewModel() {
    fun searchQuery(value: String) {
        uiState.update { it.copy(query = value) }
    }

    val uiState = MutableStateFlow(SearchUiState())
}

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onSearchChanged: (String) -> Unit
) {
    Column {
        JSearch(
            value = uiState.query,
            label = JTextResource.Text("Search"),
            onValueChanged = onSearchChanged
        )
        val words = ('A'..'Z').toList()
        LazyColumn {
            items(words) {
                SearchableItem(label = it, highlight = uiState.query.contains(it, ignoreCase = true))
            }
        }
    }
}

@Composable
fun SearchableItem(label: Char, highlight: Boolean) {
    val annotatedString = buildAnnotatedString {
        if (highlight) {
            pushStyle(SpanStyle(color = JDesignSystem.colorTheme.primary))
            append(label.toString())
            pop()
        } else {
            append(label.toString())
        }
    }
    JText(
        text = JTextResource.AnnotatedText(annotatedString),
        style = JDesignSystem.typography.body
    )
}

@Preview(showBackground = true, widthDp = 500, heightDp = 200)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        uiState = SearchUiState(query = "test")
    ) {}
}